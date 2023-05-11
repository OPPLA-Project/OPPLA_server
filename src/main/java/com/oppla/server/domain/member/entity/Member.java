package com.oppla.server.domain.member.entity;

import com.oppla.server.domain.member.enums.Gender;
import com.oppla.server.domain.member.enums.MemberStatus;
import com.oppla.server.global.common.TimeStamped;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    private String snsType;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "review_score")
    @Builder.Default
    private Integer reviewScore = 0;

    @Column
    @Builder.Default
    private Integer point = 700;

    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MemberStatus status = MemberStatus.ACTIVE;

    @Column
    @Builder.Default
    private String intro = "";

    @Column
    @Builder.Default
    private Boolean question_TF = Boolean.TRUE;
}
