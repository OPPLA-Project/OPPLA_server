package com.oppla.server.domain.question.entity;

import com.oppla.server.domain.member.entity.Member;
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

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Column
    private Long laititude;

    @Column
    private Long longitude;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String gender;

    @Column
    private String status;

    @Column(name = "finish_time")
    private LocalDateTime finishTime;

    @Column
    private Boolean selection;

    @Column
    private Integer recentPoint;
}
