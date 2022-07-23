package com.JJP.restapiserver.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id;

    private Long stage_id;

    private Long challenge_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Lob
    private String text;

    private int parent;

    private int depth;

    private int comment_state;

    @Builder

    public Comment(Long id, Long stage_id, Long challenge_id, Member member, String text, int parent, int depth, int comment_state) {
        this.id = id;
        this.stage_id = stage_id;
        this.challenge_id = challenge_id;
        this.member = member;
        this.text = text;
        this.parent = parent;
        this.depth = depth;
        this.comment_state = comment_state;
    }
}
