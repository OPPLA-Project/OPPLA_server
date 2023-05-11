package com.oppla.server.domain.auth.controller;

import com.oppla.server.domain.auth.dto.AuthReqDto;
import com.oppla.server.domain.auth.service.AuthService;
import com.oppla.server.global.common.response.BaseDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @ResponseBody
    @RequestMapping("/kakao")
    public BaseDataResponse<AuthReqDto> kakaoAuth(@RequestBody AuthReqDto authReqDto) {
        return new BaseDataResponse<>(authService.kakaoLogin(authReqDto));
    }
}
