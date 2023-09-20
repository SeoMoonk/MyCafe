package com.seomoon.boundedContext.cafe.model.entity;

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
public class Cafe extends BaseEntity {

    private String cafeName;

    private String introduction;

    private int cafeMemberLimit;

    @Enumerated(EnumType.STRING)
    private OpenType isOpen;

    @Enumerated(EnumType.STRING)
    private NameType nameType;

    @Enumerated(EnumType.STRING)
    private CafeSubject subject;

//    @OneToMany(mappedBy = "linkedCafe")
//    private List<Post> cafePostList;

//    private Img cafeImg;
//    private String cafeTag;

}
