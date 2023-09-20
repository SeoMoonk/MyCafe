package com.seomoon.boundedContext.cafe.model.entity;

import lombok.Getter;

@Getter
public enum OpenType {

    PUBLIC("public"),
    PRIVATE("private");

    private String state;

    OpenType(String state) {
        this.state = state;
    }

}
