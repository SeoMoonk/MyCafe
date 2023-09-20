package com.seomoon.boundedContext.member.service;

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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> ObyLoginId = memberRepository.findByLoginId(username);

        if (ObyLoginId.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        Member tryMember = ObyLoginId.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".contains(tryMember.getNickname())) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }
        return new User(tryMember.getLoginId(), tryMember.getPassword(), authorities);
    }

}
