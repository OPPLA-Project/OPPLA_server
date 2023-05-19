package com.oppla.server.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerAreaResDto {
    @Schema(description = "주요 답변 지역 이름")
    private String areaName;
    @Schema(description = "주요 답변 지역이 차지하는 비중")
    private Double percent;
}
