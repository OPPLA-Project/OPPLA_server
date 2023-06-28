package com.oppla.server.domain.answer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AnswerPostReqDto {
    @Schema(description = "답변이 달릴 질문의 Id")
    @NotNull
    private Long questionId;
    @Schema(description = "답변 내용")
    @NotNull
    private String content;
}
