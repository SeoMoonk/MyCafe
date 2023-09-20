package com.seomoon.boundedContext.cafe.entity;

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
