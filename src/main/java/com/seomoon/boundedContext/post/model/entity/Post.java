package com.seomoon.boundedContext.post.model.entity;

import com.seomoon.base.entity.BaseEntity;
import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import com.seomoon.boundedContext.member.model.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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

    @Column(length = 5000)
    private String markdownContent;

    @Column(length = 5000)
    private String htmlContent;

    @ManyToOne
    private Member linkedMember;

    @ManyToOne
    private Cafe linkedCafe;

//    private Img postImg;


}
