package com.seomoon.boundedContext.cafeMember.model.entity;


import com.seomoon.base.entity.BaseEntity;
import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import com.seomoon.boundedContext.cafeMember.model.role.Grade;
import com.seomoon.boundedContext.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
public class CafeMember extends BaseEntity {

    @ManyToOne
    private Cafe linkedCafe;

    @ManyToOne
    private Member linkedMember;

    @Enumerated(EnumType.STRING)
    private Grade cafeGrade;

}
