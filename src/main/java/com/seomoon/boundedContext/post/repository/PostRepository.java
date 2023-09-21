package com.seomoon.boundedContext.post.repository;

import com.seomoon.boundedContext.post.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
