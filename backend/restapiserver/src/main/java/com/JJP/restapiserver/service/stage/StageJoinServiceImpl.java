package com.JJP.restapiserver.service.stage;

import com.JJP.restapiserver.domain.dto.MessageResponse;
import com.JJP.restapiserver.domain.dto.stage.StageCompleteDto;
import com.JJP.restapiserver.domain.dto.stage.StageJoinRequestDto;
import com.JJP.restapiserver.domain.dto.stage.StageResponseDto;
import com.JJP.restapiserver.domain.entity.member.Member;
import com.JJP.restapiserver.domain.entity.stage.Stage;
import com.JJP.restapiserver.domain.entity.stage.StageUser;
import com.JJP.restapiserver.repository.member.MemberRepository;
import com.JJP.restapiserver.repository.stage.StageRepository;
import com.JJP.restapiserver.repository.stage.StageUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StageJoinServiceImpl implements StageJoinService{

    private final StageUserRepository stageUserRepository;
    private final MemberRepository memberRepository;
    private final StageRepository stageRepository;

    @Transactional
    @Override
    public Long joinStage(Long member_id, StageJoinRequestDto stageJoinRequestDto) {
        Member member = memberRepository.getById(member_id);
        if(member.getId() == null){
            new MessageResponse("해당 유저가 없습니다. id=" + member_id);
            return null;
        }
        Stage stage = stageRepository.getById(stageJoinRequestDto.getStage_id());
        if(stage.getId() == null){
            new MessageResponse("해당 스테이지가 없습니다. id=" + stage.getId());
            return null;
        }
        return stageUserRepository.save(stageJoinRequestDto.toEntity(member, stage)).getId();
    }

    @Transactional
    @Override
    public Long completeStage(Long member_id, StageCompleteDto stageCompleteDto) {
        StageUser entity = stageUserRepository.findByMember_idAndStage_id(member_id, stageCompleteDto.getStage_id()).orElseThrow(() -> new IllegalArgumentException("스테이지에 참여하지 않았습니다. id=" + member_id));

        entity.complete();

        return stageCompleteDto.getId();
    }

    @Override
    public Long joinedStageNum(Long member_id) {
        Long num = stageUserRepository.countByMember_id(member_id);
        return num;
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
}