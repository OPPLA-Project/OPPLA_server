package com.oppla.server.domain.auth.controller;

import com.oppla.server.domain.auth.dto.AuthReqDto;
import com.oppla.server.domain.auth.dto.AuthResponse;
import com.oppla.server.domain.auth.service.AuthService;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.global.common.response.BaseDataResponse;
import com.oppla.server.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "카카오 로그인", description = "카카오 계정으로 로그인 및 회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "소셜 로그인 성공")
    })
    @ResponseBody
    @PostMapping ("/kakao")
    public BaseDataResponse<AuthResponse> kakaoAuth(@RequestBody AuthReqDto authReqDto) {
        return new BaseDataResponse<>(AuthResponse.builder()
                .appToken(authService.kakaoLogin(authReqDto).getToken())
                .build());
    }

    @Operation(summary = "네이버 로그인", description = "네이버 계정으로 로그인 및 회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "소셜 로그인 성공")
    })
    @ResponseBody
    @PostMapping ("/naver")
    public BaseDataResponse<AuthResponse> naverAuth(@RequestBody AuthReqDto authReqDto) {
        return new BaseDataResponse<>(AuthResponse.builder()
                .appToken(authService.naverLogin(authReqDto).getToken())
                .build());
    }

    @Operation(summary = "회원탈퇴", description = "회원 탈퇴")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공")
    })
    @ResponseBody
    @PatchMapping ("/member")
    public BaseResponse withdrawal(@Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        authService.withdrawal(member);
        return new BaseResponse();
    }

    @Operation(summary = "JWT 토큰 임시 발급", description = "Test를 토큰 위한 임시 발급 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "소셜 로그인 성공")
    })
    @GetMapping ("/test/{memberId}")
    public BaseDataResponse<AuthResponse> testAuth(@PathVariable(name = "memberId") Long memberId) {
        return new BaseDataResponse<>(AuthResponse.builder()
                .appToken(authService.getTestJWT(memberId).getToken())
                .build());
    }
}
