package com.oppla.server.domain.notification.entity;

import com.oppla.server.global.common.TimeStamped;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class Notification extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String content;
}
