package com.oppla.server.domain.answer.service;

import com.oppla.server.domain.answer.dto.AnswerListResDto;
import com.oppla.server.domain.answer.entity.Answer;
import com.oppla.server.domain.answer.entity.AnswerImg;
import com.oppla.server.domain.answer.repository.AnswerImgRepository;
import com.oppla.server.domain.answer.repository.AnswerRepository;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.repository.MemberRepository;
import com.oppla.server.domain.question.entity.Question;
import com.oppla.server.domain.question.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AnswerServiceTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AnswerImgRepository answerImgRepository;

    @Autowired
    private AnswerService answerService;

    @Test
    @Transactional
    public void getAnswerListTest() throws Exception{
        //given
        /**
         * = Member =
         * 질문자, 답변자1, 답변자2
         *
         * = Question =
         * 질문
         *
         * = Answer =
         * 답변1, 답변2
         *
         * = 답변 이미지 =
         * 이미지1, 이미지2
         *
         */

        // Member
        Member member1 = Member.builder()
                .nickname("member1")
                .email("member1@test.com")
                .build();

        Member member2 = Member.builder()
                .nickname("member2")
                .email("member2@test.com")
                .reviewScore(10)
                .build();

        Member member3 = Member.builder()
                .nickname("member3")
                .email("member3@test.com")
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        List<Member> memberList = new ArrayList<>();

        memberList.add(member3);
        memberList.add(member2);

        // Question
        Question question = Question.builder()
                .member(member1)
                .title("Question1")
                .content("Question test content")
                .build();
        Question questionId = questionRepository.save(question);

        // Answer
        List<Answer> answerList = new ArrayList<>();

        answerList.add(
                Answer.builder()
                        .question(question)
                        .member(member3)
                        .content("answer test "+(0))
                        .selection(false)
                        .build()
        );
        for(int i=0; i<9; i++){
            answerList.add(
                    Answer.builder()
                            .question(question)
                            .member(member2)
                            .content("answer test "+(i+1))
                            .selection(false)
                            .build()
            );
        }
        answerList.add(
                Answer.builder()
                        .question(question)
                        .member(member2)
                        .content("answer test "+(10))
                        .selection(true)
                        .build()
        );


        answerRepository.saveAll(answerList);

        AnswerImg answerImg1 = AnswerImg.builder()
                .answer(answerList.get(0))
                .imgUrl("http://answer1.test.img1")
                .build();

        AnswerImg answerImg2 = AnswerImg.builder()
                .answer(answerList.get(0))
                .imgUrl("http://answer1.test.img2")
                .build();

        List<AnswerImg> answerImgList = new ArrayList<>();
        answerImgList.add(answerImg1);
        answerImgList.add(answerImg2);
        answerImgRepository.saveAll(answerImgList);


        //when
        List<AnswerListResDto> dtoList = answerService.getAnswerList(questionId.getId());

        //then
        for (int i=0; i<2; i++) {
            System.out.println("=== "+(i+1) + "번째 Test ===");
            // 답변자 일치 확인
            System.out.println("답변자 일치 확인");
            assertThat(dtoList.get(i).getMemberId()).isEqualTo(memberList.get(i).getId());
            System.out.println("답변자 일치.");

            // 답변 내용 일치 확인
            System.out.println("답변 내용 일치 확인");
            assertThat(dtoList.get(i).getContent()).isEqualTo(answerList.get(i).getContent());
            System.out.println("답변 내용 일치.");

        }

        System.out.println("== 답변자의 후기점수 확인 ==");
        assertThat(dtoList.get(2).getReviewScore()).isEqualTo(10);
        System.out.println("답변자의 후기점수 일치.");

        System.out.println("== 답변자의 답변개수 확인 ==");
        assertThat(dtoList.get(2).getAnswerNum()).isEqualTo(10);
        System.out.println("답변자의 답변개수 일치.");

        System.out.println("== 답변자의 채택률 확인 ==");
        assertThat(dtoList.get(2).getSelectionRate()).isEqualTo(10.00);
        System.out.println("답변자의 채택률 일치.");

    }
}