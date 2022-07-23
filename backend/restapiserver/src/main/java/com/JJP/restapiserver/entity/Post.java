package com.JJP.restapiserver.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member writer;
    @Column(length = 45)
    private String title;

    @Lob
    private String content;

    private Long stage_id;

    @Column(length = 300)
    private String post_img;

    @Builder

    public Post(Long id, Member writer, String title, String content, Long stage_id, String post_img) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.stage_id = stage_id;
        this.post_img = post_img;
    }
}
