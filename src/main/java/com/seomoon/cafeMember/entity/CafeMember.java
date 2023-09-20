package com.seomoon.cafeMember.entity;


import com.seomoon.cafe.entity.Cafe;
import com.seomoon.cafeMember.role.Role;
import com.seomoon.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CafeMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Cafe linkedCafe;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Member member;

    @Enumerated(EnumType.STRING)
    private Role challengerRole;

    @CreatedDate
    private LocalDateTime createDate;

}
