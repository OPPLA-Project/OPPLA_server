package com.oppla.server.domain.auth.client;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.oppla.server.domain.auth.dto.AuthReqDto;
import com.oppla.server.domain.auth.dto.KakaoMemberResDto;
import com.oppla.server.domain.auth.dto.SnsType;
import com.oppla.server.domain.auth.exception.TokenValidFailedException;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.enums.Gender;
import com.oppla.server.domain.member.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.h2.util.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

@Log4j2
@Component
@RequiredArgsConstructor
public class ClientKakao {

    private final GradeRepository gradeRepository;

    public String getKakaoAccessToken(String code) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://kauth.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        AuthReqDto authReqDto = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", "65433f410686d60f2b664ce88403019e")
                        .queryParam("redirect_uri", "http://localhost:8080/auth/kakao")
                        .queryParam("code", code)
                        .build())
                .retrieve()
                .bodyToMono(AuthReqDto.class)
                .block();

        return authReqDto.getAccessToken();
    }

    public Member getUserData(String accessToken) {
        try {
            WebClient webClient = WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            KakaoMemberResDto kakaoMemberResDto = webClient.get()
                    .uri(new URI("https://kapi.kakao.com/v2/user/me"))
                    .headers(h -> h.setBearerAuth(accessToken))
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException()))
                    .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new RuntimeException("Internal Server Error")))
                    .bodyToMono(KakaoMemberResDto.class)
                    .block();
            log.info(kakaoMemberResDto.getKakao_account().getEmail());
            log.info(kakaoMemberResDto.getKakao_account().getGender());
            log.info(kakaoMemberResDto.getKakao_account().getProfile().getIs_default_image());

            return Member.builder()
                    .snsType(SnsType.KAKAO.toString())
                    .nickname(kakaoMemberResDto.getKakao_account().getProfile().getNickname())
                    .email(kakaoMemberResDto.getKakao_account().getEmail())
                    .profileUrl(kakaoMemberResDto.getKakao_account().getProfile().getProfile_image_url())
                    .grade(gradeRepository.findById(1L).get())
                    .gender(Gender.from(kakaoMemberResDto.getKakao_account().getGender(), SnsType.KAKAO))
                    .build();
        } catch(URISyntaxException e) {
            throw new IllegalArgumentException();
        }
    }
}
