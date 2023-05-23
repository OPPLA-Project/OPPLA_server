package com.oppla.server.domain.question.dto;

import com.oppla.server.domain.answer.dto.AnswerListResDto;
import com.oppla.server.domain.question.entity.Question;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionListByMeResDto {
    @Schema(description = "질문 Id")
    private Long questionId;

    /*@Schema(description = "장소명")
    private String locationName;*/

    /*@Schema(description = "질문 등록 시간")
    private LocalDateTime createdAt;*/

    @Schema(description = "질문 제목")
    private String title;

    @Schema(description = "답변 개수")
    private Integer answerCount;

    @Schema(description = "종료 여부")
    private Boolean finish;

    @Schema(description = "채택 여부")
    private Boolean selection;

    /*public QuestionListByMeResDto(Question question, Integer answerCount) {
        this.questionId = question.getId();
        this.locationName = question.getLocationName();
        this.createdAt = question.getCreatedAt();
        this.title = question.getTitle();
        this.answerCount = answerCount;
        this.selection = question.getSelection();
    }*/
}
