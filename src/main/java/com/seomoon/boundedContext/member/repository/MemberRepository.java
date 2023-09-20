package com.seomoon.boundedContext.member.repository;

import com.seomoon.boundedContext.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByNickname(String nickname);

}
