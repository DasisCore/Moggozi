package com.JJP.restapiserver.domain.entity.stage;

import com.JJP.restapiserver.domain.dto.CommentRequestDto;
import com.JJP.restapiserver.domain.entity.BaseTimeEntity;
import com.JJP.restapiserver.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Entity
@Getter
@AllArgsConstructor
@Builder
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="stage_id")
    private Stage stage;

    // 멤버와 다대일 양방향 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Lob
    private String text;

    private Long parent;

    private int depth;

    private int comment_state;

    public void update(CommentRequestDto commentRequestDto)
    {
        this.text = commentRequestDto.getText();
        this.parent = commentRequestDto.getParent();
        this.depth = commentRequestDto.getDepth();
        this.comment_state = commentRequestDto.getComment_state();
    }
}
