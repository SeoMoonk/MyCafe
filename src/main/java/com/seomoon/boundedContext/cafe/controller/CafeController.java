package com.seomoon.boundedContext.cafe.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String cafeCreateForm(CafeCreateForm cafeCreateForm) {

        return "view/cafe/createForm";
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String createCafe(@Valid CafeCreateForm cafeCreateForm,
                             BindingResult bindingResult,
                             Principal principal) {

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
            return "/error";
        } else{
            model.addAttribute("cafe", result);
            return "view/cafe/cafeDetail";
        }
    }
}
