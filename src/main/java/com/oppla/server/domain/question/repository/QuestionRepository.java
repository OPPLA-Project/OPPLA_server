package com.oppla.server.domain.question.repository;

import com.oppla.server.domain.question.entity.Question;
import com.oppla.server.domain.question.repository.impl.QuestionDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionDslRepository {
}
