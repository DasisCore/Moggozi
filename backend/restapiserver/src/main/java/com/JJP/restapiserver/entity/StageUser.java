package com.JJP.restapiserver.entity;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
public class StageUser {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    private LocalDateTime join_time;

    private LocalDateTime end_time;

    @Builder
    public StageUser(Long id, Member member, Stage stage, LocalDateTime join_time, LocalDateTime end_time) {
        this.id = id;
        this.member = member;
        this.stage = stage;
        this.join_time = join_time;
        this.end_time = end_time;
    }
}
