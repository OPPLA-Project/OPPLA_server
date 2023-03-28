package com.oppla.server.domain.member.entity;

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
    private Integer reviewScore;

    @Column
    private Integer point;

    @Column
    private String gender;

    @Column
    private String status;

    @Column
    private String intro;

    @Column
    private Boolean question_TF;

    public Member(String nickname, String email, String snsType, String profileUrl) {
        this.nickname = nickname;
        this.email = email;
        this.snsType = snsType;
        this.profileUrl = profileUrl;
    }

}
