package com.oppla.server.domain.question.repository;

import com.oppla.server.domain.question.entity.Question;
import com.oppla.server.domain.question.repository.impl.QuestionDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionDslRepository {
    List<Question> findAllByMemberIdOrderByCreatedAtDesc(Long id);
}
