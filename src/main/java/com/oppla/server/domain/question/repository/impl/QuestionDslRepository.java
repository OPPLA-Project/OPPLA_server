package com.oppla.server.domain.question.repository.impl;

import com.oppla.server.domain.member.enums.Gender;
import com.oppla.server.domain.question.dto.QuestionResDto;

import java.util.List;

public interface QuestionDslRepository {
    List<QuestionResDto> findQuestionByGenderAndLocation(Gender gender, Double latitude, Double longitude);
}
