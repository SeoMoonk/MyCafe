package com.seomoon.boundedContext.cafeMember.entity;


import com.seomoon.boundedContext.cafeMember.role.Role;
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
