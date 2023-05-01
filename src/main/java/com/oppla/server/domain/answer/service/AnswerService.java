package com.oppla.server.domain.answer.service;

import com.oppla.server.domain.answer.dto.AnswerPostReqDto;
import com.oppla.server.domain.answer.entity.Answer;
import com.oppla.server.domain.answer.repository.AnswerRepository;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.question.exception.NotFoundQuestionException;
import com.oppla.server.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;


    /**
     * == 답변달기 ==
     * @param member, dto
     * @throws NotFoundQuestionException -- dto 안의 id에 해당하는 Question이 DB에 없을 경우
     */
    public void postAnswer(Member member, AnswerPostReqDto dto){

        answerRepository.save(
                Answer.builder()
                        .question(
                                questionRepository.findById(dto.getQuestionId())
                                        .orElseThrow(NotFoundQuestionException::new)
                        )
                        .member(member)
                        .content(dto.getContent())
                        .selection(false)
                        .recentPoint(member.getPoint())
                .build()
        );


    }
}
