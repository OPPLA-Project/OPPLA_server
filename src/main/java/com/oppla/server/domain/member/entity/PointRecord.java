package com.oppla.server.domain.member.entity;

import com.oppla.server.domain.member.enums.PointRecordDesc;
import com.oppla.server.global.common.TimeStamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class PointRecord extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_record_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Column
    private PointRecordDesc description;

    @Column
    private Integer deal_point;

    @Column
    private Integer rest_point;
}
