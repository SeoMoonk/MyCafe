package com.seomoon.boundedContext.cafeMember.service;

import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import com.seomoon.boundedContext.cafe.service.CafeService;
import com.seomoon.boundedContext.cafeMember.model.entity.CafeMember;
import com.seomoon.boundedContext.cafeMember.model.role.Grade;
import com.seomoon.boundedContext.cafeMember.repository.CafeMemberRepository;
import com.seomoon.boundedContext.member.model.entity.Member;
import com.seomoon.boundedContext.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeMemberService {

    private final CafeMemberRepository cafeMemberRepository;
    private final MemberService memberService;
    private final CafeService cafeService;

    @Transactional
    public void joinCafe(String loginId,Long cafeId){

        Member memberByLoginId = memberService.getMemberByLoginId(loginId);
        Cafe cafeById = (Cafe) cafeService.getCafeById(cafeId).get("result");

        CafeMember newCafeMember = CafeMember.builder()
                .linkedMember(memberByLoginId)
                .linkedCafe(cafeById)
                .cafeGrade(Grade.USER)
                .build();

        cafeMemberRepository.save(newCafeMember);
    }

    @Transactional
    public void createCafe(String creatorUsername, Cafe newCafe){

        Member memberByLoginId = memberService.getMemberByLoginId(creatorUsername);

        CafeMember newCafeMember = CafeMember.builder()
                .linkedMember(memberByLoginId)
                .linkedCafe(newCafe)
                .cafeGrade(Grade.ADMIN)
                .build();

        cafeMemberRepository.save(newCafeMember);
    }

}
