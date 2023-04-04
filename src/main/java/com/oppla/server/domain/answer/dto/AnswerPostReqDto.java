package com.oppla.server.domain.answer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AnswerPostReqDto {
    @NotNull
    private Long questionId;
    @NotNull
    private String content;
}
