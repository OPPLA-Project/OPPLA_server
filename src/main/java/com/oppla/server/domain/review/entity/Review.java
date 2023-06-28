package com.oppla.server.domain.review.entity;

import com.oppla.server.domain.answer.entity.Answer;
import com.oppla.server.domain.question.entity.Question;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.global.common.TimeStamped;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class Review extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name="answerer_id")
    private Member member;

    @Column
    private Long score;

    @Column
    private Integer infoScore;

    @Column
    private Integer speedScore;
}
