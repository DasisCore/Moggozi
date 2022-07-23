package com.JJP.restapiserver.entity;

import lombok.Getter;

import javax.persistence.*;

// 완전 복합키로 가져야 하지 않나?
@Entity
@Getter
public class ChallengeLike {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;
}
