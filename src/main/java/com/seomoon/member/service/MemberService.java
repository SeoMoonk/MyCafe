package com.seomoon.member.service;

import com.seomoon.member.entity.Member;
import com.seomoon.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String createMember(String loginId, String password, String nickname){

        if(checkValidJoin(loginId, nickname)){

            Member member = Member.builder()
                    .loginId(loginId)
                    .password(password) //TODO PasswordEncorder from security
                    .nickname(nickname)
                    .build();

            memberRepository.save(member);

            return "S-1";
        }else{
            return "F-1";
        }

    }

    public boolean checkValidJoin(String loginId, String nickname){

        Optional<Member> ObyId = memberRepository.findByLoginId(loginId);
        if(ObyId.isPresent()){
            return false;
        }

        Optional<Member> ObyNickname = memberRepository.findByNickname(nickname);
        if(ObyNickname.isPresent()){
            return false;
        }

        return true;
    }



}
