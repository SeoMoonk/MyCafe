package com.seomoon.boundedContext.cafeMember.controller;

import com.seomoon.boundedContext.cafeMember.service.CafeMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/cafeMember")
@RequiredArgsConstructor
public class CafeMemberController {

    private final CafeMemberService cafeMemberService;

    @GetMapping("/join")
    public String joinCafe(@RequestParam(value="id") Long cafeId, Principal principal) {

        String loginId = principal.getName();

        cafeMemberService.joinCafe(loginId, cafeId);

        return "redirect:/";
    }

}
