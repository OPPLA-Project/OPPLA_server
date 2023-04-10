package com.oppla.server.domain.answer.repository;

import com.oppla.server.domain.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestionId(Long questionId);
    Integer countByMemberId(Long id);

    Integer countByMemberIdAndSelection(Long id, boolean b);
}
