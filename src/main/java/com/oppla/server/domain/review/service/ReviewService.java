package com.oppla.server.domain.review.service;

import com.oppla.server.domain.answer.entity.Answer;
import com.oppla.server.domain.answer.exception.AnswerNotFoundException;
import com.oppla.server.domain.answer.repository.AnswerRepository;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.question.entity.Question;
import com.oppla.server.domain.question.exception.QuestionNotFoundException;
import com.oppla.server.domain.question.repository.QuestionRepository;
import com.oppla.server.domain.review.dto.ReviewPostReqDto;
import com.oppla.server.domain.review.entity.Review;
import com.oppla.server.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    public void postReview(Member member, ReviewPostReqDto dto, Long answerId) {

        // 리뷰를 등록할 답변 조회
        Answer answer = answerRepository.findById(answerId)
                                        .orElseThrow(AnswerNotFoundException::new);

        // 답변이 속한 질문 조회
        Question question = questionRepository.findById(answer.getQuestion().getId())
                                        .orElseThrow(QuestionNotFoundException::new);

        // 리뷰 등록
        reviewRepository.save(
                Review.builder()
                        .answer(answer)
                        .question(question)
                        .member(member)
                        .score(dto.getScore())
                        .infoScore(dto.getInfoScore())
                        .speedScore(dto.getSpeedScore())
                        .build()
        );
    }
}
