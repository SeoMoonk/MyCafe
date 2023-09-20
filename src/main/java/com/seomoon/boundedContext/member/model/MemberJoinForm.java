package com.seomoon.boundedContext.member.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinForm {

    @Size(min=3, max=25)
    @NotEmpty(message="ID는 필수 입력 사항입니다.")
    private String loginId;

    @NotEmpty(message="비밀번호는 필수 입력 사항입니다.")
    private String password1;

    @NotEmpty(message="비밀번호 확인은 필수 입력 사항입니다.")
    private String password2;

    @NotEmpty(message="닉네임은 필수 입력 사항입니다.")
    private String nickname;

}
