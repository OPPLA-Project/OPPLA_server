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
public class InfoScoreResDto {
    @Schema(description = "후기평가 1의 개수")
    private Integer infoScore1Count;
    @Schema(description = "후기평가 2의 개수")
    private Integer infoScore2Count;
    @Schema(description = "후기평가 3의 개수")
    private Integer infoScore3Count;
    @Schema(description = "후기평가 4의 개수")
    private Integer infoScore4Count;
}
