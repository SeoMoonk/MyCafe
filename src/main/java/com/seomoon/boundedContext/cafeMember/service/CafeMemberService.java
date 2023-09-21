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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeMemberService {

    private final CafeMemberRepository cafeMemberRepository;

    @Transactional
    public Map<String, String> joinCafe(Member member,Cafe cafe, Grade grade){

        Map<String, String> findResultMap = getByMemberAndCafe(member, cafe);

        if(findResultMap.get("code").startsWith("S")){
            CafeMember newCafeMember = CafeMember.builder()
                    .linkedMember(member)
                    .linkedCafe(cafe)
                    .cafeGrade(grade)
                    .build();

            cafeMemberRepository.save(newCafeMember);
        }

        return findResultMap;
    }

    public Map<String, String> getByMemberAndCafe(Member member, Cafe cafe){

        Optional<CafeMember> byLinkedMemberAndLinkedCafe =
                cafeMemberRepository.findByLinkedMemberAndLinkedCafe(member, cafe);

        Map<String, String> findResultMap = new HashMap<>();

        String code = "S-1";
        String msg = "가입 가능한 ID 입니다";

        if(byLinkedMemberAndLinkedCafe.isPresent()){
            code = "F-1";
            msg = "이미 가입중인 카페입니다.";
        }

        findResultMap.put("code", code);
        findResultMap.put("msg", msg);

        return findResultMap;
    }

}
