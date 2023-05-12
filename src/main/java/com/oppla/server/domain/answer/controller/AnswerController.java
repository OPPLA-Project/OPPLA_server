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

    @Operation(
            summary = "답변 채택",
            description = "질문자가 Question에 달린 답변중 1개를 채택한다.\n" +
                    "1. 채택된 Answer Selection 값 변경 (false -> true)\n" +
                    "2. 채택한 Question Selection 값 변경 (flase -> true)\n" +
                    "3. 답변자 Point + 500, 질문자 Point + 200(보증금)\n" +
                    "4. 답변자와 질문자의 PointRecord 업데이트"

    )
    @PatchMapping("/list/{answerId}/selection")
    public BaseResponse pickAnswer(@PathVariable("answerId") Long answerId){
        answerService.pickAnswer(answerId);

        return new BaseResponse();
    }

}
