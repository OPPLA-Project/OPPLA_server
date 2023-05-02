package com.oppla.server.domain.answer.repository;

import com.oppla.server.domain.answer.entity.Answer;
import com.oppla.server.domain.answer.repository.impl.AnswerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long>, AnswerRepositoryCustom {
    Integer countByMemberId(Long id);

    Integer countByMemberIdAndSelection(Long id, boolean b);
}
