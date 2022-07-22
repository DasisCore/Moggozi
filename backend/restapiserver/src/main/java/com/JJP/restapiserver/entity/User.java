package com.JJP.restapiserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @NotNull
    @Column(length = 30)
    @Email
    private String email;

    @NotNull
    @Column(length = 20)
    private String username;

    @NotNull
    @Column(length = 10)
    private String nickname;

    @NotNull
    @Column(length = 100)
    private String password;

    @Column(length = 50)
    private String introduce;

    private int is_private; // 0: false, 1: true

    private String img;

    // 사용자 상태 (권한) (ROLE_INVALIDATED_USER, ROLE_VALIDATED_USER, ROLE_ADMIN)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    /** Getter, Setter -> 생성자로 변경 예정)
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
    }*/
}
