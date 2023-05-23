package com.oppla.server.domain.question.repository.impl;

import com.oppla.server.domain.member.enums.Gender;
import com.oppla.server.domain.question.dto.QuestionListByMeResDto;
import com.oppla.server.domain.question.dto.QuestionListResDto;
import com.oppla.server.domain.question.dto.QuestionSpecResDto;
import com.oppla.server.domain.question.entity.Question;

import java.util.List;

public interface QuestionDslRepository {
    List<QuestionListResDto> findQuestionByGenderAndLocation(Gender gender, Double latitude, Double longitude);
}
