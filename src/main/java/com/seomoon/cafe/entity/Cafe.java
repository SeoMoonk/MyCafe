package com.seomoon.cafe.entity;

import com.seomoon.member.entity.Member;
import com.seomoon.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cafeName;

    private String cafeIntroduction;

    private int cafeMemberLimit;

//    @OneToMany(mappedBy = "linkedCafe")
//    private List<Post> cafePostList;

    @CreatedDate
    private LocalDateTime createDate;

//    private Img cafeImg;
//    private String cafeTag;

}
