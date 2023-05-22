package com.oppla.server.domain.question.service;

import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.enums.Gender;
import com.oppla.server.domain.question.dto.QuestionListResDto;
import com.oppla.server.domain.question.dto.QuestionPostReqDto;
import com.oppla.server.domain.question.dto.QuestionSpecResDto;
import com.oppla.server.domain.question.entity.Question;
import com.oppla.server.domain.question.enums.QuestionStatus;
import com.oppla.server.domain.question.exception.QuestionNotFoundException;
import com.oppla.server.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    public void postQuestion(Member member, QuestionPostReqDto questionPostReqDto) {
        questionRepository.save(
                Question.builder()
                        .member(member)
                        .locationName(questionPostReqDto.getLocationName())
                        .latitude(questionPostReqDto.getLatitude())
                        .longitude(questionPostReqDto.getLongitude())
                        .title(questionPostReqDto.getTitle())
                        .content(questionPostReqDto.getContent())
                        .finishTime(questionPostReqDto.getFinishTime())
                        .gender(Gender.from(questionPostReqDto.getGender()))
                        .status(QuestionStatus.ACTIVE)
                        .selection(Boolean.FALSE)
                        .build()
        );
    }


    public List<QuestionListResDto> getQuestionList(Gender gender, Double lat, Double lng) {
        return questionRepository.findQuestionByGenderAndLocation(gender, lat, lng);
    }

    public QuestionSpecResDto getQuestionSpec(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new QuestionNotFoundException());

        return new QuestionSpecResDto(question);
    }
}
