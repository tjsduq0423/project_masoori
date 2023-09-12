package com.fintech.masoori.domain.card.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "basic")
@ToString(of = {"id", "keyword", "totalAmount", "frequency"})
public class Basic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basic_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "frequency")
    private int frequency;
}
