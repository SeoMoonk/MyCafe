package com.seomoon.boundedContext.post.controller;

import com.seomoon.boundedContext.member.model.entity.Member;
import com.seomoon.boundedContext.post.model.dto.PostResultDto;
import com.seomoon.boundedContext.post.model.dto.PostWriteRequest;
import com.seomoon.boundedContext.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/write")
    public String getWriteForm(@RequestParam(value = "id") String cafeId, Model model) {

        model.addAttribute("cafeId", cafeId);

        return "/view/post/writeForm";
    }

    @ResponseBody
    @PostMapping("/write")
    public PostResultDto writePost(@RequestBody @Valid PostWriteRequest postWriteRequest,
                                   @AuthenticationPrincipal Member member) {
        Long postId = postService.writePost(postWriteRequest, member);

        return new PostResultDto(postId);
    }

}
