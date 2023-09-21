package com.seomoon.boundedContext.cafeMember.controller;

import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import com.seomoon.boundedContext.cafe.service.CafeService;
import com.seomoon.boundedContext.cafeMember.model.role.Grade;
import com.seomoon.boundedContext.cafeMember.service.CafeMemberService;
import com.seomoon.boundedContext.member.model.entity.Member;
import com.seomoon.boundedContext.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/cafeMember")
@RequiredArgsConstructor
public class CafeMemberController {

    private final CafeMemberService cafeMemberService;
    private final MemberService memberService;
    private final CafeService cafeService;

    @GetMapping("/join")
    public String joinCafe(@RequestParam(value="id") Long cafeId, Principal principal) {

        String loginId = principal.getName();

        Member memberByLoginId = memberService.getMemberByLoginId(loginId);
        Cafe cafeById = (Cafe) cafeService.getCafeById(cafeId).get("result");

        cafeMemberService.joinCafe(memberByLoginId, cafeById, Grade.USER);

        return "redirect:/";
    }

}
