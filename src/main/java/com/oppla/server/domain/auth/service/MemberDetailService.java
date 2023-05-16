package com.oppla.server.domain.auth.service;

import com.oppla.server.domain.auth.dto.CustomMemberDetails;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public CustomMemberDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member findMember = memberRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        CustomMemberDetails memberDetail = new CustomMemberDetails(findMember);

        return memberDetail;
    }
}
