package com.seomoon.boundedContext.cafe.model.cafeEnum;

import lombok.Getter;

@Getter
public enum CafeSubject {

    TRAVEL("travel"),
    FOOD("food"),
    STUDY("study");

    private String subject;

    CafeSubject(String subject) {
        this.subject = subject;
    }
}
