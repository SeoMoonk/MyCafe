package com.seomoon.boundedContext.member.model.entity;

import com.seomoon.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
public class Member extends BaseEntity {

    private String loginId;

    private String password;

    private String nickname;

/*    @OneToMany(mappedBy = "member")
    private List<CafeMember> challengeMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Post> myPostList = new ArrayList<>();*/

}
