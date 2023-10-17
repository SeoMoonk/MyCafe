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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Post getPostById(Long postId) {

        Optional<Post> ObyId = postRepository.findById(postId);

        if(ObyId.isPresent()){
            return ObyId.get();
        } else{
            //FIXME
            return null;
        }
    }

    public List<Post> getPostListByLinkedCafe(Cafe linkedCafe) {

        return postRepository.findAllByLinkedCafe(linkedCafe);
    }

    @Transactional
    public void deletePost(Post post, String loginMember) {

        //FIXME 삭제 유효성 어떻게 사용할 것인지?
        Map<String, String> deleteResultMap = new HashMap<>();

        String code = "S-1";
        String msg = "삭제가 완료되었습니다.";

        if(!post.getLinkedMember().getLoginId().equals(loginMember)){
            code = "F-1";
            msg = "자신의 포스트가 아닙니다.";
        } else {
            postRepository.delete(post);
        }

        deleteResultMap.put("code", code);
        deleteResultMap.put("mgs", msg);
    }

    @Transactional
    public Long modifyPost(PostWriteRequest postWriteRequest, Member writer){

        String title = postWriteRequest.getTitle();
        String htmlContent = postWriteRequest.getHtmlBody();
        String markdownContent = postWriteRequest.getMarkdownBody();
        Long postId = postWriteRequest.getPostId();
        Post postById = getPostById(postId);

        Post modifiedPost = postById.toBuilder()
                .postTitle(title)
                .htmlContent(htmlContent)
                .markdownContent(markdownContent)
                .build();

        postRepository.save(modifiedPost);

        return modifiedPost.getId();
    }

}
