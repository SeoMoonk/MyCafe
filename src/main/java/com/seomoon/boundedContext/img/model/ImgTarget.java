package com.seomoon.boundedContext.img.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ImgTarget {

    POST_IMG("게시글 이미지"),
    CAFE_IMG("카페 이미지");

    ImgTarget(String target) {
        this.target = target;
    }

    private String target;

}
