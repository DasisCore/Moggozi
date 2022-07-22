package com.JJP.restapiserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
public class UserDto {

    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String nickname;

    private String introduce;

    @NotNull
    private String password;

    private int is_private; // 0: false, 1: true

    private String img;

    /** 추후 INTEGER type으로 변경 예정 **/
    private String state;

    @Builder
    public UserDto(String email, String username, String nickname, String introduce
            , String password, int is_private, String img, String state) {
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.introduce = introduce;
        this.password = password;
        this.is_private = is_private;
        this.img = img;
        this.state = state;
    }


}
