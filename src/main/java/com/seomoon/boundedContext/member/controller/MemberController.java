package com.seomoon.boundedContext.member.controller;

import com.seomoon.boundedContext.member.model.MemberJoinForm;
import com.seomoon.boundedContext.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(MemberJoinForm memberJoinForm) {

        return "view/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid MemberJoinForm memberJoinForm,
                       BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "view/joinForm";
        }

        Map<String, String> joinResultMap = memberService.createMember(memberJoinForm);

        if(joinResultMap.get("code").startsWith("F")){

            String failCode = joinResultMap.get("code");
            String failMsg = joinResultMap.get("msg");

            bindingResult.reject("global.error", failCode + ": " + failMsg);

            return "view/joinForm";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {

        return "view/loginForm";
    }

    @PostMapping("/login")
    public String login() {

        //todo login service

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {

        return "/";
    }

}
