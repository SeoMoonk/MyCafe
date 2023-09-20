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

    public CafeSubject getNameType(String subjectCode) {

        if(subjectCode.equals("travel")){
            return CafeSubject.TRAVEL;
        } else if(subjectCode.equals("food")){
            return CafeSubject.FOOD;
        } else{
            return CafeSubject.STUDY;
        }
    }
}
