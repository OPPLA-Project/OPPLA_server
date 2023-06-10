package com.oppla.server.domain.auth.client;

import com.oppla.server.domain.auth.dto.KakaoMemberResDto;
import com.oppla.server.domain.auth.dto.NaverMemberResDto;
import com.oppla.server.domain.auth.dto.SnsType;
import com.oppla.server.domain.auth.exception.TokenValidFailedException;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.enums.Gender;
import com.oppla.server.domain.member.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientNaver {
    private final GradeRepository gradeRepository;

    public Member getUserData(String accessToken) {
        try {
            WebClient webClient = WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            NaverMemberResDto naverMemberResDto = webClient.get()
                    .uri(new URI("https://openapi.naver.com/v1/nid/me"))
                    .headers(h -> h.setBearerAuth(accessToken))
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException()))
                    .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new RuntimeException("Internal Server Error")))
                    .bodyToMono(NaverMemberResDto.class)
                    .block();

            return Member.builder()
                    .snsType(SnsType.NAVER.toString())
                    .nickname(naverMemberResDto.getResponse().getName())
                    .email(naverMemberResDto.getResponse().getEmail())
                    .profileUrl(naverMemberResDto.getResponse().getProfile_image())
                    .grade(gradeRepository.findById(1L).get())
                    .gender(Gender.from(naverMemberResDto.getResponse().getGender(), SnsType.NAVER))
                    .build();
        } catch(URISyntaxException e) {
            throw new IllegalArgumentException();
        }
    }
}
