package com.oppla.server.domain.answer.repository.impl;

import com.oppla.server.domain.answer.entity.Answer;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface AnswerRepositoryCustom {
    List<Answer> findAllByQuestionId(Long questionId, Pageable pageable);
}
