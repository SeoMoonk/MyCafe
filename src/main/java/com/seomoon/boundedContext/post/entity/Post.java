package com.seomoon.boundedContext.post.entity;

import com.seomoon.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
public class Post extends BaseEntity {

    private String postTitle;

    private String postContent;

    private String writer;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Cafe linkedCafe;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Member member;

//    private Img postImg;


}
