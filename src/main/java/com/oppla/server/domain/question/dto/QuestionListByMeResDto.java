package com.oppla.server.domain.question.dto;

import com.oppla.server.domain.answer.dto.AnswerListResDto;
import com.oppla.server.domain.question.entity.Question;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionListByMeResDto {
    @Schema(description = "질문 Id")
    private Long questionId;

    @Schema(description = "장소명")
    private String locationName;

    @Schema(description = "질문 등록 시간")
    private LocalDateTime createdAt;

    @Schema(description = "질문 제목")
    private String title;

    @Schema(description = "답변 개수")
    private Integer answerCount;

    @Schema(description = "채택 여부")
    private Boolean selection;

    @QueryProjection
    public QuestionListByMeResDto(Question question, List<AnswerListResDto> answerListResDtos) {
        this.questionId = question.getId();
        this.locationName = question.getLocationName();
        this.createdAt = question.getCreatedAt();
        this.title = question.getTitle();
        this.answerCount = answerListResDtos.size();
        this.selection = question.getSelection();
    }
}
