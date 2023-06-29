package com.oppla.server.domain.review.controller;

import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.review.dto.ReviewPostReqDto;
import com.oppla.server.domain.review.service.ReviewService;
import com.oppla.server.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(
            summary = "리뷰 등록",
            description = "리뷰 등록 API"
    )
    @PostMapping("/{answerId}")
    public BaseResponse postQuestion(@Parameter(hidden = true) @AuthenticationPrincipal Member member,
                                     @RequestBody ReviewPostReqDto dto,
                                     @PathVariable("answerId") Long answerId) {
        reviewService.postReview(member, dto, answerId);

        return new BaseResponse();
    }
}
