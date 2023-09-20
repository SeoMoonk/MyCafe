package com.seomoon.cafeMember.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("카페장"), USER("일반회원");
    private final String grade;
}
