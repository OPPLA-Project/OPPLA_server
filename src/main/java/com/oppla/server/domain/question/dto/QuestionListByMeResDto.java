package com.oppla.server.domain.question.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionListByMeResDto {
    @Schema(description = "질문 Id")
    private Long questionId;

    @Schema(description = "질문 제목")
    private String title;

    @Schema(description = "답변 개수")
    private Integer answerCount;

    @Schema(description = "종료 여부")
    private Boolean finish;

    @Schema(description = "채택 여부")
    private Boolean selection;
}
