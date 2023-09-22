package com.seomoon.boundedContext.post.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostWriteRequest {

    @NotBlank
    private String title;

    private String htmlBody;

    private String markdownBody;

    private Long cafeId;

    private Long postId;

}