package com.oppla.server.domain.answer.controller;

import com.oppla.server.domain.answer.dto.AnswerListResDto;
import com.oppla.server.domain.answer.dto.AnswerPostReqDto;
import com.oppla.server.domain.answer.service.AnswerService;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.global.common.response.BaseDataResponse;
import com.oppla.server.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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

    @Operation(
            summary = "답변 목록 조회",
            description = "한 질문에 달린 답변 목록을 조회하는 API, Pagination 적용"
    )
    @GetMapping("/list/{questionId}")
    public BaseDataResponse<List<AnswerListResDto>> getAnswerList(@PathVariable("questionId") Long questionId, Pageable pageable){

        return BaseDataResponse.<List<AnswerListResDto>>builder()
                .result(answerService.getAnswerList(questionId, pageable))
                .build();
    }


}
