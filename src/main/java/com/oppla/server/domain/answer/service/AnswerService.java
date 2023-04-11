package com.oppla.server.domain.answer.service;

import com.oppla.server.domain.answer.dto.AnswerPostReqDto;
import com.oppla.server.domain.answer.dto.AnswerListResDto;
import com.oppla.server.domain.answer.entity.Answer;
import com.oppla.server.domain.answer.entity.AnswerImg;
import com.oppla.server.domain.answer.repository.AnswerImgRepository;
import com.oppla.server.domain.answer.repository.AnswerRepository;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.question.exception.NotFoundQuestionException;
import com.oppla.server.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    private final AnswerImgRepository answerImgRepository;


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

    public List<AnswerListResDto> getAnswerList(Long questionId) {

        return answerRepository.findAllByQuestionId(questionId).stream().map(answer -> {
                // 답변 수
                Integer answerNum = answerRepository.countByMemberId(answer.getMember().getId());
                // 채택된 수
                Integer selectionNum = answerRepository.countByMemberIdAndSelection(answer.getMember().getId(), true);
                // 답변 이미지
                List<String> imgList = answerImgRepository.findAllByAnswerId(answer.getId())
                        .stream().map(AnswerImg::getImgUrl).collect(Collectors.toList());

                 return AnswerListResDto.builder()
                            .memberId(answer.getMember().getId())
                            .reviewScore(answer.getMember().getReviewScore())
                            .answerNum(answerNum)
                            .selectionRate((Math.round((double) selectionNum / (double) answerNum * 100.0*100)/100.0))
                            .content(answer.getContent())
                            .imgList(imgList)
                            .build();
                }
        ).collect(Collectors.toList());
    }
}
