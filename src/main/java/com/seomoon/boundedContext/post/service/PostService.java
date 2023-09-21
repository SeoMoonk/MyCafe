package com.seomoon.boundedContext.post.service;

import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import com.seomoon.boundedContext.cafe.service.CafeService;
import com.seomoon.boundedContext.member.model.entity.Member;
import com.seomoon.boundedContext.post.model.dto.PostWriteRequest;
import com.seomoon.boundedContext.post.model.entity.Post;
import com.seomoon.boundedContext.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final CafeService cafeService;

    @Transactional
    public Long writePost(PostWriteRequest postWriteRequest, Member writer){

        String title = postWriteRequest.getTitle();
        String htmlContent = postWriteRequest.getHtmlBody();
        String markdownContent = postWriteRequest.getMarkdownBody();
        Cafe cafeById = (Cafe) cafeService.getCafeById(postWriteRequest.getCafeId()).get("result");

        Post newPost = Post.builder()
                .postTitle(title)
                .htmlContent(htmlContent)
                .markdownContent(markdownContent)
                .linkedCafe(cafeById)
                .linkedMember(writer)
                .build();

        postRepository.save(newPost);

        return newPost.getId();
    }

}
