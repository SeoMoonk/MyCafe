package com.seomoon.boundedContext.post.controller;

import com.seomoon.boundedContext.member.model.entity.Member;
import com.seomoon.boundedContext.member.service.MemberService;
import com.seomoon.boundedContext.post.model.dto.PostResultDto;
import com.seomoon.boundedContext.post.model.dto.PostWriteRequest;
import com.seomoon.boundedContext.post.model.entity.Post;
import com.seomoon.boundedContext.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/write")
    public String getWriteForm(@RequestParam(value = "id") String cafeId, Model model) {

        model.addAttribute("cafeId", cafeId);

        return "/view/post/writeForm";
    }

    @ResponseBody
    @PostMapping("/write")
    public PostResultDto writePost(@RequestBody @Valid PostWriteRequest postWriteRequest, Principal principal) {

        String loginId = principal.getName();

        Member writer = memberService.getMemberByLoginId(loginId);

        Long postId = postService.writePost(postWriteRequest, writer);

        return new PostResultDto(postId);
    }

    @GetMapping("/detail")
    public String postDetail(@RequestParam(value="id")Long postId, Model model) {

        Post postById = postService.getPostById(postId);

        String cafeName = postById.getLinkedCafe().getCafeName();

        model.addAttribute("post", postById);
        model.addAttribute("cafeName", cafeName);
        model.addAttribute("cafe", postById.getLinkedCafe());

        return "/view/post/postDetail";
    }

}
