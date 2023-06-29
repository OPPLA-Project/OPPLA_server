package com.oppla.server.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewPostReqDto {
    @Schema(description = "후기점수 (별점)")
    private Long score;
    @Schema(description = "후기 평가 선택 (1, 2, 3, 4) 중 택 1")
    private Integer infoScore;
    @Schema(description = "답변 속도 평가 (1, 2, 3) 중 택 1")
    private Integer speedScore;
}
