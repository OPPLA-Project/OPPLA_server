package com.oppla.server.domain.question.entity;

import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.enums.Gender;
import com.oppla.server.domain.question.enums.QuestionStatus;
import com.oppla.server.global.common.TimeStamped;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class Question extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Column
    private String locationName;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    @Enumerated(EnumType.STRING)
    private QuestionStatus status;

    @Column(name = "finish_time")
    private LocalDateTime finishTime;

    @Column
    private Boolean selection;

    public void changeSelectionStatus(Boolean status){
        this.selection = status;
    }
}
