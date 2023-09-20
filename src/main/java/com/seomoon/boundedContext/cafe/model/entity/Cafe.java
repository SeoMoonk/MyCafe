package com.seomoon.boundedContext.cafe.model.entity;

import com.seomoon.base.entity.BaseEntity;
import com.seomoon.boundedContext.cafe.model.cafeEnum.CafeSubject;
import com.seomoon.boundedContext.cafe.model.cafeEnum.NameType;
import com.seomoon.boundedContext.cafe.model.cafeEnum.OpenType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
public class Cafe extends BaseEntity {

    private String cafeName;

    private String introduction;

    @Enumerated(EnumType.STRING)
    private OpenType isOpen;

    @Enumerated(EnumType.STRING)
    private NameType nameType;

    @Enumerated(EnumType.STRING)
    private CafeSubject subject;

    private int memberLimit;

//    @OneToMany(mappedBy = "linkedCafe")
//    private List<Post> cafePostList;

//    private Img cafeImg;
//    private String cafeTag;

}
