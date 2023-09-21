package com.seomoon.boundedContext.cafeMember.repository;

import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import com.seomoon.boundedContext.cafeMember.model.entity.CafeMember;
import com.seomoon.boundedContext.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeMemberRepository extends JpaRepository<CafeMember, Long> {

    Optional<CafeMember> findByLinkedMemberAndLinkedCafe(Member member, Cafe cafe);

    int countByLinkedCafe(Cafe cafe);

}
