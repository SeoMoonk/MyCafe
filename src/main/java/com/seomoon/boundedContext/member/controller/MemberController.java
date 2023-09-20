package com.seomoon.boundedContext.member.controller;

import com.seomoon.boundedContext.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm() {

        return "/view/joinForm";
    }

    @PostMapping("/join")
    public String join(@RequestParam(value = "loginId") String loginId,
                       @RequestParam(value = "password") String password,
                       @RequestParam(value = "nickname") String nickname) {

        String createRsCode = memberService.createMember(loginId, password, nickname);

        if(createRsCode.startsWith("F")){
            //실패
            //FIXME
            //RedirectAttributes attr;

            return "redirect:/view/joinForm";
        } else {
            //성공
            return "redirect:/";
        }


    }

}
