package com.oppla.server.domain.review.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerSpeedResDto {
    @Schema(description = "답변속도 빠름의 개수")
    private Integer answerFastCount;
    @Schema(description = "답변속도 적당의 개수")
    private Integer answerSosoCount;
    @Schema(description = "답변속도 느림의 개수")
    private Integer answerSlowCount;
}
