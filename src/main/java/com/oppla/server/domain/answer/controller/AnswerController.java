package com.oppla.server.domain.answer.controller;

import com.oppla.server.domain.answer.dto.AnswerPostReqDto;
import com.oppla.server.domain.answer.service.AnswerService;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;

    @Operation(
            summary = "답변 등록",
            description = "답변 등록 API"
    )
    @PostMapping("")
    public BaseResponse postAnswer(@AuthenticationPrincipal Member member, @RequestBody AnswerPostReqDto dto){
        answerService.postAnswer(member, dto);

        return new BaseResponse();
    }
}
