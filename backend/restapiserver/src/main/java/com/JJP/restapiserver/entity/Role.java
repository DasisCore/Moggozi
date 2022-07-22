package com.JJP.restapiserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private ERole role_name;

}
