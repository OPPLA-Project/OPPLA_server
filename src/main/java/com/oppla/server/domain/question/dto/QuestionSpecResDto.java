package com.oppla.server.domain.question.dto;

import com.oppla.server.domain.question.entity.Question;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class QuestionSpecResDto {
    @Schema(description = "질문 Id")
    private Long questionId;

    @Schema(description = "장소명")
    private String locationName;

    @Schema(description = "위도")
    private Double latitude;

    @Schema(description = "경도")
    private Double longitude;

    @Schema(description = "질문 제목")
    private String title;

    @Schema(description = "질문 내용")
    private String content;

    @Schema(description = "남은 시간(분)")
    private Long restMinute;

    public QuestionSpecResDto(Question question) {
        this.questionId = question.getId();
        this.locationName = question.getLocationName();
        this.latitude = question.getLatitude();
        this.longitude = question.getLongitude();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.restMinute = calculateRestMinute(question.getFinishTime());
    }

    private Long calculateRestMinute(LocalDateTime finishTime) {
        LocalDateTime now = LocalDateTime.now();

        return ChronoUnit.MINUTES.between(now, finishTime);
    }
}
