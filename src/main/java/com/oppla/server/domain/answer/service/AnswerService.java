package com.oppla.server.domain.answer.service;

import com.oppla.server.domain.answer.dto.AnswerPostReqDto;
import com.oppla.server.domain.answer.dto.AnswerListResDto;
import com.oppla.server.domain.answer.entity.Answer;
import com.oppla.server.domain.answer.entity.AnswerImg;
import com.oppla.server.domain.answer.exception.AnswerNotFoundException;
import com.oppla.server.domain.answer.repository.AnswerImgRepository;
import com.oppla.server.domain.answer.repository.AnswerRepository;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.entity.PointRecord;
import com.oppla.server.domain.member.repository.MemberRepository;
import com.oppla.server.domain.member.repository.PointRecordRepository;
import com.oppla.server.domain.question.entity.Question;
import com.oppla.server.domain.question.exception.QuestionNotFoundException;
import com.oppla.server.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.oppla.server.domain.member.enums.PointRecordDesc.ANSWER_PICK;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    private final AnswerImgRepository answerImgRepository;

    private final MemberRepository memberRepository;
    private final PointRecordRepository pointRecordRepository;


    private final Integer ANSWER_PICK_POINT = 500;
    private final Integer QUESTION_PICK_POINT = 200;
    private final Integer QUESTION_POST_POINT = 500 + QUESTION_PICK_POINT;



    /**
     * == 답변달기 ==
     * @param member, dto
     * @throws QuestionNotFoundException -- dto 안의 id에 해당하는 Question이 DB에 없을 경우
     */
    public void postAnswer(Member member, AnswerPostReqDto dto){

        answerRepository.save(
                Answer.builder()
                        .question(
                                questionRepository.findById(dto.getQuestionId())
                                        .orElseThrow(QuestionNotFoundException::new)
                        )
                        .member(member)
                        .content(dto.getContent())
                        .selection(false)
                        .build()
        );


    }

    public List<AnswerListResDto> getAnswerList(Long questionId, Pageable pageable) {

        return answerRepository.findAllByQuestionId(questionId, pageable).stream().map(answer -> {
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

    public void pickAnswer(Long answerId) {
        /**
         * 1. Answer 채택 여부 변경
         * 2. Question 채택 여부 변경
         * 3. 포인트 정산 (일단 500)
         */

        // 1. Answer 채택 여부 변경
        Answer answer = answerRepository.findById(answerId).orElseThrow(AnswerNotFoundException::new);
        answer.changeSelectionStatus(true);
        answerRepository.save(answer);

        // 2. Question 채택 여부 변경
        Question question = answer.getQuestion();
        question.changeSelectionStatus(true);
        questionRepository.save(question);

        // 3. 포인트 정산 (일단 500)
        // 3-1. 답변자 포인트 상승
        Member answerMember = answer.getMember();
        answerMember.changePoint(answerMember.getPoint() + ANSWER_PICK_POINT);

        // 3-2. 질문자 보증금 환급
        Member questionMember = question.getMember();
        questionMember.changePoint(questionMember.getPoint() + QUESTION_PICK_POINT);

        // 4. PointRecord 업데이트
        // 4-1. 답변자
        PointRecord answerPointRecord = PointRecord.builder()
                .member(answerMember)
                .description(ANSWER_PICK)
                .deal_point(ANSWER_PICK_POINT)
                .rest_point(answerMember.getPoint())
                .build();

        // 4-2. 질문자
        PointRecord questionPointRecord = PointRecord.builder()
                .member(questionMember)
                .description(ANSWER_PICK)
                .deal_point(QUESTION_PICK_POINT)
                .rest_point(questionMember.getPoint())
                .build();

        pointRecordRepository.saveAll(Arrays.asList(answerPointRecord, questionPointRecord));

    }
}
