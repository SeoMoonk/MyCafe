package com.seomoon.boundedContext.cafeMember.model.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Grade {

    ADMIN("카페장"), USER("일반회원");
    private final String grade;
}
