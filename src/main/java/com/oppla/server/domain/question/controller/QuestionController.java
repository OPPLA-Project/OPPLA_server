package com.oppla.server.domain.question.controller;

import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.question.dto.QuestionListByMeResDto;
import com.oppla.server.domain.question.dto.QuestionListResDto;
import com.oppla.server.domain.question.dto.QuestionPostReqDto;
import com.oppla.server.domain.question.dto.QuestionSpecResDto;
import com.oppla.server.domain.question.service.QuestionService;
import com.oppla.server.global.common.response.BaseDataResponse;
import com.oppla.server.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BaseResponse postQuestion(@Parameter(hidden = true) @AuthenticationPrincipal Member member,
                                     @RequestBody QuestionPostReqDto questionPostReqDto) {
        questionService.postQuestion(member, questionPostReqDto);

        return new BaseResponse();
    }

    @Operation(
            summary = "주변 질문 목록 보기",
            description = "주변 질문 목록 API"
    )
    @GetMapping("/around")
    public BaseDataResponse<List<QuestionListResDto>> aroundQuestion(@Parameter(hidden = true) @AuthenticationPrincipal Member member,
                                                                     @RequestParam("lat") Double latitude, @RequestParam("lng") Double longitude) {
        List<QuestionListResDto> aroundQuestionList = questionService.getQuestionList(member.getGender(), latitude, longitude);

        return new BaseDataResponse<>(aroundQuestionList);
    }

    @Operation(
            summary = "질문 상세 보기",
            description = "질문 상세보기 API"
    )
    @GetMapping("/spec/{questionId}")
    public BaseDataResponse<QuestionSpecResDto> questionSpec(@PathVariable("questionId") Long questionId) {
        QuestionSpecResDto questionSpec = questionService.getQuestionSpec(questionId);

        return new BaseDataResponse<>(questionSpec);
    }

    @Operation(
            summary = "내가 한 질문 보기",
            description = "내가 한 질문 보기 API"
    )
    @GetMapping("/list")
    public BaseDataResponse<List<QuestionListByMeResDto>> myQuestionList(@Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        return new BaseDataResponse<>(questionService.getMyQuestionList(member));
    }
}