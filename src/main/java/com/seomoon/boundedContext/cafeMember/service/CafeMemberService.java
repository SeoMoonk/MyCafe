package com.seomoon.boundedContext.cafeMember.service;

import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import com.seomoon.boundedContext.cafeMember.model.entity.CafeMember;
import com.seomoon.boundedContext.cafeMember.model.role.Grade;
import com.seomoon.boundedContext.cafeMember.repository.CafeMemberRepository;
import com.seomoon.boundedContext.member.model.entity.Member;
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

        Map<String, String> checkResultMap = checkValidJoin(member, cafe);

        if(checkResultMap.get("code").startsWith("S")){
            CafeMember newCafeMember = CafeMember.builder()
                    .linkedMember(member)
                    .linkedCafe(cafe)
                    .cafeGrade(grade)
                    .build();

            cafeMemberRepository.save(newCafeMember);
        }

        return checkResultMap;
    }

    public Map<String, String> checkValidJoin(Member member, Cafe cafe){

        Optional<CafeMember> byLinkedMemberAndLinkedCafe =
                cafeMemberRepository.findByLinkedMemberAndLinkedCafe(member, cafe);

        int memberCount = cafeMemberRepository.countByLinkedCafe(cafe);

        Map<String, String> findResultMap = new HashMap<>();

        String code = "S-1";
        String msg = "카페에 가입되었습니다.";

        if(byLinkedMemberAndLinkedCafe.isPresent()){
            code = "F-1";
            msg = "카페에 가입할 수 없습니다. (사유 : 이미 가입중인 카페)";
        } else if(memberCount >= cafe.getMemberLimit()) {
            code = "F-2";
            msg = "카페에 가입할 수 없습니다. (사유 : 인원수 초과)";
        }

        findResultMap.put("code", code);
        findResultMap.put("msg", msg);

        return findResultMap;
    }

}
