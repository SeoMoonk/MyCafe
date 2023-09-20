package com.seomoon.boundedContext.cafe.model.cafeEnum;

import lombok.Getter;

@Getter
public enum NameType {

    NICK_NAME("nickname"),
    REAL_NAME("realname");

    private String nameType;

    NameType(String nameType) {
        this.nameType = nameType;
    }
}
