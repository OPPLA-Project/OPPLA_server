package com.oppla.server.domain.answer.dto;

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
    /**
     * == 답변자 정보 ==
     * 1. 답변자 id
     * 2. 답변자 후기 점수
     * 3. 답변자 답변수
     * 4. 답변자 채택률
     *
     *  == 답변 보기 ==
     *  1. 내용
     *  2. 답변 이미지
     */

    private Long memberId;
    private Integer reviewScore; // 후기점수가 평균이면 Long아닌가욤
    private Integer answerNum;
    private Long selectionRate;

    private String content;
    private List<String> imgList;
}
