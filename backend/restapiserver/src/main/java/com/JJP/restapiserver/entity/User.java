package com.JJP.restapiserver.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

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

    /** 1: validated user, 2: invalidated user, 3: administrator 변경 예정 */
    private String state;

    @Builder
    public User(String email, String username, String nickname, String introduce
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
