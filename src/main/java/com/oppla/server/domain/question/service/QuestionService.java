package com.oppla.server.domain.question.service;

import com.oppla.server.domain.answer.repository.AnswerRepository;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.enums.Gender;
import com.oppla.server.domain.question.dto.QuestionListByMeResDto;
import com.oppla.server.domain.question.dto.QuestionListResDto;
import com.oppla.server.domain.question.dto.QuestionPostReqDto;
import com.oppla.server.domain.question.dto.QuestionSpecResDto;
import com.oppla.server.domain.question.entity.Question;
import com.oppla.server.domain.question.enums.QuestionStatus;
import com.oppla.server.domain.question.exception.QuestionNotFoundException;
import com.oppla.server.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.oppla.server.domain.question.entity.QQuestion.question;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
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

    public List<QuestionListByMeResDto> getMyQuestionList(Member member) {
        return questionRepository.findAllByMemberIdOrderByCreatedAtDesc(member.getId()).stream().map(question -> {
            LocalDateTime now = LocalDateTime.now();
            return QuestionListByMeResDto.builder()
                    .questionId(question.getId())
                    .title(question.getTitle())
                    .answerCount(answerRepository.countByQuestionId(question.getId()))
                    .finish(question.getFinishTime().isBefore(now))
                    .selection(question.getSelection())
                    .build();
        }).collect(Collectors.toList());
    }
}
