package com.seomoon.boundedContext.post.repository;

import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import com.seomoon.boundedContext.post.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByLinkedCafe(Cafe linkedCafe);

}
