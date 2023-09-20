package com.seomoon.post.entity;

import com.seomoon.cafe.entity.Cafe;
import com.seomoon.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postTitle;

    private String postContent;

    private String writer;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Cafe linkedCafe;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Member member;

    @CreatedDate
    private LocalDateTime createDate;

//    private Img postImg;


}
