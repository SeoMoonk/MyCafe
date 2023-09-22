package com.seomoon.boundedContext.cafe.controller;

import com.seomoon.boundedContext.cafe.config.CafeConfigProperties;
import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import com.seomoon.boundedContext.cafe.model.form.CafeCreateForm;
import com.seomoon.boundedContext.cafe.service.CafeService;
import com.seomoon.boundedContext.cafeMember.service.CafeMemberService;
import com.seomoon.boundedContext.member.model.entity.Member;
import com.seomoon.boundedContext.member.service.MemberService;
import com.seomoon.boundedContext.post.model.entity.Post;
import com.seomoon.boundedContext.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cafe")
public class CafeController {

    private final CafeService cafeService;
    private final CafeMemberService cafeMemberService;
    private final MemberService memberService;
    private final PostService postService;
    private final CafeConfigProperties cafeConfigProperties;

    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String cafeCreateForm(CafeCreateForm cafeCreateForm) {

        return "view/cafe/createForm";
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String createCafe(@Valid CafeCreateForm cafeCreateForm,
                             BindingResult bindingResult,
                             Principal principal) throws IOException {

        String loginId = principal.getName();

        Map<String, String> createResultMap = cafeService.createCafe(cafeCreateForm, loginId);

        if(createResultMap.get("code").startsWith("F")){

            String failCode = createResultMap.get("code");
            String failMsg = createResultMap.get("msg");

            bindingResult.reject("global.error", failCode + ": " + failMsg);

            return "view/cafe/createForm";
        }

        return "redirect:/";
    }

    @GetMapping("/detail")
    @PreAuthorize("isAuthenticated()")
    public String getCafeDetail(@RequestParam(value = "id") Long cafeId, Model model, Principal principal,
                                @RequestParam(value = "failMsg", required = false, defaultValue = "none") String failMsg,
                                @RequestParam(value = "successMsg", required = false, defaultValue = "none") String successMsg) {

        Map<String, Object> getCafeMap = cafeService.getCafeById(cafeId);
        Cafe result = (Cafe) getCafeMap.get("result");

        String loginId = principal.getName();
        Member memberByLoginId = memberService.getMemberByLoginId(loginId);

        Integer memberLimit = cafeConfigProperties.getMemberLimit();
        Integer memberCount = cafeMemberService.getCountMemberByLinkedCafe(result);
        model.addAttribute("memberLimit", memberLimit);
        model.addAttribute("memberCount", memberCount);

        List<Post> postListByLinkedCafe = postService.getPostListByLinkedCafe(result);
        model.addAttribute("postList", postListByLinkedCafe);

        //FIXME 컨트롤러에 서비스같은 로직이 너무 많음.

        //이미 가입중인 카페인지 아닌지 체크
        if(cafeMemberService.checkValidJoin(memberByLoginId, result).get("code")
                .equals("F-1")){
            model.addAttribute("isJoin", true);
        } else{
            model.addAttribute("isJoin", false);
        }

        if(!failMsg.equals("none")){
            model.addAttribute("failMsg", failMsg);
        }else if(!successMsg.equals("none")){
            model.addAttribute("successMsg", successMsg);
        }

        if(getCafeMap.get("code").toString().startsWith("F")){
            //FIXME 에러페이지로 내가 보낸다는게 말이 되나
            return "/error";
        } else{
            model.addAttribute("cafe", result);
            return "view/cafe/cafeDetail";
        }
    }
}
