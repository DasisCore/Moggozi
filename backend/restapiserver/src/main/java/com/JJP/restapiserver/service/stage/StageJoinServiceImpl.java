package com.JJP.restapiserver.service.stage;

import com.JJP.restapiserver.domain.dto.MessageResponse;
import com.JJP.restapiserver.domain.dto.SliceListDto;
import com.JJP.restapiserver.domain.dto.stage.MyStageDto;
import com.JJP.restapiserver.domain.dto.stage.StageCompleteDto;
import com.JJP.restapiserver.domain.dto.stage.StageJoinRequestDto;
import com.JJP.restapiserver.domain.dto.stage.StageResponseDto;
import com.JJP.restapiserver.domain.entity.member.Member;
import com.JJP.restapiserver.domain.entity.member.MemberScore;
import com.JJP.restapiserver.domain.entity.stage.Stage;
import com.JJP.restapiserver.domain.entity.stage.StageUser;
import com.JJP.restapiserver.repository.member.MemberRepository;
import com.JJP.restapiserver.repository.member.MemberScoreRepository;
import com.JJP.restapiserver.repository.stage.StageRepository;
import com.JJP.restapiserver.repository.stage.StageUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StageJoinServiceImpl implements StageJoinService{

    private final StageUserRepository stageUserRepository;
    private final MemberRepository memberRepository;
    private final StageRepository stageRepository;
    private final MemberScoreRepository memberScoreRepository;

    @Transactional
    @Override
    public Long joinStage(StageJoinRequestDto stageJoinRequestDto) {
        Member member = memberRepository.getById(stageJoinRequestDto.getMember_id());
        if(member.getId() == null){
            new MessageResponse("해당 유저가 없습니다. id=" + member);
            return null;
        }
        Stage stage = stageRepository.getById(stageJoinRequestDto.getStage_id());
        if(stage.getId() == null){
            new MessageResponse("해당 스테이지가 없습니다. id=" + stage);
            return null;
        }
        if(!stageUserRepository.findByMember_idAndStage_id(member.getId(), stage.getId()).isEmpty()){
            System.out.println("already joined");
            return Long.valueOf(-1);
        }
        return stageUserRepository.save(stageJoinRequestDto.toEntity(member, stage)).getId();
    }

    @Transactional
    @Override
    public Long changeStageState(StageCompleteDto stageCompleteDto, int state) {
        StageUser entity = stageUserRepository.findByMember_idAndStage_id(stageCompleteDto.getMember_id(), stageCompleteDto.getStage_id()).orElseThrow(() -> new IllegalArgumentException("스테이지에 참여하지 않았습니다. id=" + stageCompleteDto.getStage_id()));

        MemberScore memberScore = memberScoreRepository.getById(stageCompleteDto.getMember_id());

        if(entity.getState() != state){
            entity.setState(state);

            if(state == 2){
                memberScore.addScore(1L);
            } else {
                memberScore.addScore((long) -1);
            }
        }

        return entity.getId();
    }

    @Override
    public Long joinedStageNum(Long member_id) {
        Long num = stageUserRepository.countByMember_id(member_id);
        return num;
    }

    @Override
    public void deleteJoin(Long stage_id, Long member_id) {
        StageUser entity = stageUserRepository.findByMember_idAndStage_id(member_id, stage_id).orElseThrow(() -> new IllegalArgumentException("해당 스테이지에 참여하지 않았습니다. id=" + stage_id));
        stageUserRepository.delete(entity);
    }

    @Override
    public List<StageResponseDto> joinedStageList8(Long member_id) {
        List<StageUser> stageUserList = stageUserRepository.findTop8ByMember_idOrderByJoinTimeDesc(member_id);
        List<StageResponseDto> stageResponseDtoList = new ArrayList<>();
        if(stageUserList != null){
            for(StageUser stageUser : stageUserList){
                Stage stage = stageUser.getStage();
                stageResponseDtoList.add(new StageResponseDto(stage));
            }
        }
        return stageResponseDtoList;
    }

    @Override
    public int stateStage(Long member_id, Long stage_id){
        Optional<StageUser> entity = stageUserRepository.findByMember_idAndStage_id(member_id, stage_id);

        if(entity.isEmpty()){
            return 0;
        }

        return entity.get().getState();
    }

    @Override
    public SliceListDto infiniteStageList(Long member_id, Pageable pageable) {
        Slice<StageUser> stageUserSlice = stageUserRepository.findByMember_Id(member_id, pageable);

        // stageUser -> stage_id -> stage
        List<Long> stage_idx = stageUserSlice.stream().map(o -> o.getStage().getId()).collect(Collectors.toList());
        // stage_id -> stage
        List<Stage> stageList = stageRepository.findByIdIn(stage_idx);
        List<MyStageDto> myStageDtoList = new ArrayList<>();
        for(Stage stage : stageList){
            int state = -1;
            for(StageUser stageUser : stageUserSlice){
                if(stageUser.getStage().getId() == stage.getId()){
                    state = stageUser.getState();
                }
            }
            MyStageDto myStageDto = MyStageDto.builder()
                    .id(stage.getId())
                    .img(stage.getImg())
                    .name(stage.getName())
                    .state(state)
                    .build();
            myStageDtoList.add(myStageDto);
        }

        SliceListDto myPageStageDto = SliceListDto.builder()
                .pageNum(stageUserSlice.getNumber())
                .content(myStageDtoList)
                .size(stageUserSlice.getSize())
                .hasNext(stageUserSlice.hasNext())
                .build();
        return myPageStageDto;
    }
}