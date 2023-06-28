package com.oppla.server.domain.question.dto;

import com.oppla.server.domain.member.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class QuestionPostReqDto {
    private String locationName;
    private Double latitude;
    private Double longitude;
    private String title;
    private String content;
    private LocalDateTime finishTime;
    private String gender;
}
