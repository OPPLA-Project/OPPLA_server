package com.oppla.server.domain.question.controller;

import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.question.dto.QuestionPostReqDto;
import com.oppla.server.domain.question.service.QuestionService;
import com.oppla.server.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    @Operation(
            summary = "질문 등록",
            description = "질문 등록 API"
    )
    @PostMapping("")
    public BaseResponse postQuestion(@AuthenticationPrincipal Member member, @RequestBody QuestionPostReqDto questionPostReqDto){
        questionService.postQuestion(member, questionPostReqDto);

        return new BaseResponse();
    }
}
