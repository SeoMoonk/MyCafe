package com.seomoon.member.entity;

import com.seomoon.cafe.entity.Cafe;
import com.seomoon.cafeMember.entity.CafeMember;
import com.seomoon.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    @CreatedDate
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "member")
    private List<CafeMember> challengeMemberList;

    @OneToMany(mappedBy = "member")
    private List<Post> myPostList = new ArrayList<>();

}
