package com.oppla.server.domain.auth.service;

import antlr.TokenStreamRewriteEngine;
import com.oppla.server.domain.auth.client.ClientKakao;
import com.oppla.server.domain.auth.client.ClientNaver;
import com.oppla.server.domain.auth.dto.AuthReqDto;
import com.oppla.server.domain.auth.dto.AuthToken;
import com.oppla.server.domain.auth.dto.Role;
import com.oppla.server.domain.auth.dto.SnsType;
import com.oppla.server.domain.auth.exception.OtherSnsTypeException;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.enums.MemberStatus;
import com.oppla.server.domain.member.repository.MemberRepository;

import com.oppla.server.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final ClientKakao clientKakao;
    private final ClientNaver clientNaver;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public AuthToken kakaoLogin(AuthReqDto authReqDto) {
        Member member = clientKakao.getUserData(authReqDto.getAccessToken());
        //Optional<Member> isMember = memberRepository.findByEmail(member.getEmail());
        Optional<Member> isMember = memberRepository.findMemberBySnsTypeAndSnsMemberIdAndStatus(member.getSnsType(), member.getSnsMemberId(), MemberStatus.ACTIVE);

        if(!isMember.isPresent()) {
            if(memberRepository.existsByEmail(member.getEmail())) {
                throw new OtherSnsTypeException();
            }
            memberRepository.save(member);
            return tokenProvider.createAppToken(member.getId(), Role.USER);
        } else {
            return tokenProvider.createAppToken(isMember.get().getId(), Role.USER);
        }
    }

    public AuthToken naverLogin(AuthReqDto authReqDto) {
        Member member = clientNaver.getUserData(authReqDto.getAccessToken());
        //Optional<Member> isMember = memberRepository.findByEmail(member.getEmail());
        Optional<Member> isMember = memberRepository.findMemberBySnsTypeAndSnsMemberIdAndStatus(member.getSnsType(), member.getSnsMemberId(), MemberStatus.ACTIVE);

        if(!isMember.isPresent()) {
            if(memberRepository.existsByEmail(member.getEmail())) {
                throw new OtherSnsTypeException();
            }
            memberRepository.save(member);
            return tokenProvider.createAppToken(member.getId(), Role.USER);
        } else {
            return tokenProvider.createAppToken(isMember.get().getId(), Role.USER);
        }
    }
    public AuthToken getTestJWT(Long memberId) {
        return tokenProvider.createAppToken(memberId, Role.USER);
    }

    @Transactional
    public void withdrawal(Member member) {
        member.changeStatus();
        memberRepository.save(member);
    }
}
