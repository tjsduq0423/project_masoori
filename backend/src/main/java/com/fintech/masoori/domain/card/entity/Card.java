package com.fintech.masoori.domain.card.entity;

import com.fintech.masoori.domain.card.dto.CardType;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.global.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "card")
@ToString(of = {"id", "name", "photoPath", "description", "cardType"})
public class Card extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "card_name")
    private String name;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "description")
    private String description;

    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Challenge> challenges = new ArrayList<>();

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Basic> basics = new ArrayList<>();

}
