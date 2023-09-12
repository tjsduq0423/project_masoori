package com.fintech.masoori.domain.card.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "challenge")
@ToString(of = {"id", "isSuccess", "name", "achievementCondition", "startTime", "endTime"})
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "is_success")
    private Boolean isSuccess;

    private String name;

    @Column(name = "achievement_condition", length = 25)
    private String achievementCondition;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;
}
