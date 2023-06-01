package com.oppla.server.domain.auth.service;

import antlr.TokenStreamRewriteEngine;
import com.oppla.server.domain.auth.client.ClientKakao;
import com.oppla.server.domain.auth.dto.AuthReqDto;
import com.oppla.server.domain.auth.dto.AuthToken;
import com.oppla.server.domain.auth.dto.Role;
import com.oppla.server.domain.auth.dto.SnsType;
import com.oppla.server.domain.auth.exception.OtherSnsTypeException;
import com.oppla.server.domain.member.entity.Member;
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
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public AuthToken kakaoLogin(AuthReqDto authReqDto) {
        Member member = clientKakao.getUserData(authReqDto.getAccessToken());
        AuthToken appToken;
        Optional<Member> isMember = memberRepository.findByEmail(member.getEmail());

        if(!isMember.isPresent()) {
            memberRepository.save(member);
            appToken = tokenProvider.createAppToken(member.getId(), Role.USER);
        } else if (!isMember.get().getSnsType().equals(SnsType.KAKAO.toString())) {
            throw new OtherSnsTypeException();
        } else {
            appToken = tokenProvider.createAppToken(isMember.get().getId(), Role.USER);
        }

        return appToken;
    }
    public AuthToken getTestJWT(Long memberId) {
        return tokenProvider.createAppToken(memberId, Role.USER);
    }
}
