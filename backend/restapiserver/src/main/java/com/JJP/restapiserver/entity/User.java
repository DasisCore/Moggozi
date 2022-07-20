package com.JJP.restapiserver.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String username; // 이메일
    private String nickname;
    private String introduce; // 한줄소개
    private String password;
    private boolean is_private; // 프로필 공개여부
    private String user_img;

    /** 확인할 것 */
    private String user_state; // ???????
    private String is_staff; // 관리자 여부는 Spring Security에서 ROLE로 구분해줄거라서 필요없을것 같음

    public User(String username, String nickname, String introduce,
                String password, boolean is_private, String user_img) {
        this.username = username;
        this.nickname = nickname;
        this.introduce = introduce;
        this.password = password;
        this.is_private = is_private;
        this.user_img = user_img;
    }




    /** 다른 Entity와의 관계 설정*/
    // 팔로우

    // 포스트

    // 포스트 좋아요

    // 알림

    // 챌린지

    // 단계 사용자

    // 댓글

    // 한줄평






}
