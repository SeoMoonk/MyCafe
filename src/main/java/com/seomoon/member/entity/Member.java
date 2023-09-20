package com.seomoon.member.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    private String nickname;

    @CreatedDate
    private LocalDateTime createDate;

/*    @OneToMany(mappedBy = "member")
    private List<CafeMember> challengeMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Post> myPostList = new ArrayList<>();*/

}
