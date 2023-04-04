package com.oppla.server.domain.answer.controller;

import com.oppla.server.domain.answer.dto.AnswerPostReqDto;
import com.oppla.server.domain.answer.service.AnswerService;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.global.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("")
    public BaseResponse postAnswer(@AuthenticationPrincipal Member member, @RequestBody AnswerPostReqDto dto){
        answerService.postAnswer(member, dto);

        return new BaseResponse();
    }
}
