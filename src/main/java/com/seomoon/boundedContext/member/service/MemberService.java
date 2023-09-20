package com.seomoon.boundedContext.member.service;

import com.seomoon.boundedContext.member.model.form.MemberJoinForm;
import com.seomoon.boundedContext.member.model.entity.Member;
import com.seomoon.boundedContext.member.model.role.MemberRole;
import com.seomoon.boundedContext.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Map<String, String> createMember(MemberJoinForm memberJoinForm){

        String loginId = memberJoinForm.getLoginId();
        String password1 = memberJoinForm.getPassword1();
        String password2 = memberJoinForm.getPassword2();
        String nickname = memberJoinForm.getNickname();

        Map<String, String> checkJoinMap = checkValidJoin(loginId, password1, password2, nickname);

        if(checkJoinMap.get("code").startsWith("S")){
            Member member = Member.builder()
                    .loginId(loginId)
                    .password(passwordEncoder.encode(password1)) //TODO PasswordEncorder from security
                    .nickname(nickname)
                    .build();

            memberRepository.save(member);
        }

        return checkJoinMap;
    }

    public Map<String, String> checkValidJoin(String loginId, String password1, String password2, String nickname){

        Map<String, String> resultMap = new HashMap<>();

        String code = "S-1";
        String msg = "유효한 폼 입니다.";

        Optional<Member> ObyId = memberRepository.findByLoginId(loginId);
        if(ObyId.isPresent()){
            code = "F-1";
            msg = "이미 사용중인 아이디 입니다.";
        }

        Optional<Member> ObyNickname = memberRepository.findByNickname(nickname);
        if(ObyNickname.isPresent()){

            code = "F-2";
            msg = "이미 사용중인 닉네임 입니다.";
        }

        if(!password1.equals(password2)){
            code = "F-3";
            msg = "두가지 비밀번호가 일치하지 않습니다.";
        }

        resultMap.put("code", code);
        resultMap.put("msg", msg);

        return resultMap;
    }

}
