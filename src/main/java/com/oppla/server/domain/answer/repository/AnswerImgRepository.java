package com.oppla.server.domain.answer.repository;

import com.oppla.server.domain.answer.entity.AnswerImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerImgRepository extends JpaRepository<AnswerImg, Long> {
    List<AnswerImg> findAllByAnswerId(Long id);
}
