package com.oppla.server.domain.auth.controller;

import com.oppla.server.domain.auth.dto.AuthReqDto;
import com.oppla.server.domain.auth.dto.AuthResponse;
import com.oppla.server.domain.auth.service.AuthService;
import com.oppla.server.global.common.response.BaseDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
    @RequestMapping("/kakao")
    public BaseDataResponse<AuthResponse> kakaoAuth(@RequestBody AuthReqDto authReqDto) {
        return new BaseDataResponse<>(AuthResponse.builder()
                .appToken(authService.kakaoLogin(authReqDto).getToken())
                .build());
    }
}
