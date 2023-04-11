package com.oppla.server.domain.answer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerListResDto {

    @Schema(description = "답변 작성자 Id")
    private Long memberId;
    @Schema(description = "답변 작성자의 후기점수")
    private Integer reviewScore; // 후기점수가 평균이면 Double아닌가욤
    @Schema(description = "답변 작성자의 답변 개수")
    private Integer answerNum;
    @Schema(description = "답변 작성자의 채택률")
    private Double selectionRate;

    @Schema(description = "답변 내용")
    private String content;
    @Schema(description = "답변에 사용된 사진 목록")
    private List<String> imgList;
}
