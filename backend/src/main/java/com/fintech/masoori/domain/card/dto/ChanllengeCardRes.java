package com.fintech.masoori.domain.card.dto;

import com.fintech.masoori.domain.card.entity.Card;
import com.fintech.masoori.domain.user.entity.User;
import lombok.Data;

@Data
public class ChanllengeCardRes {
    private Long id;
    private User user;
    private String name;
    private String photoPath;
    private String description;
    private CardType cardType;

    public ChanllengeCardRes(Card card){
        this.id = card.getId();
        this.user = card.getUser();
        this.name = card.getName();
        this.photoPath = card.getPhotoPath();
        this.description = card.getDescription();
        this.cardType = card.getCardType();
    }
}
