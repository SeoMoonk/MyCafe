package com.seomoon.member.repository;

import com.seomoon.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByNickname(String nickname);

}
