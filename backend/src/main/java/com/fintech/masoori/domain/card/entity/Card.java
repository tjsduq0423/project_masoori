package com.fintech.masoori.domain.card.entity;

import com.fintech.masoori.domain.card.dto.CardType;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.global.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

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

    private String name;

    private String photoPath;

    private String description;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

}
