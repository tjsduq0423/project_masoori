package com.fintech.masoori.domain.card.dto;

import com.fintech.masoori.domain.card.entity.Card;
import com.fintech.masoori.domain.card.entity.Challenge;

import lombok.Data;

@Data
public class ChallengeCardRes {
	private Long id;
	private String name;
	private String photoPath;
	private String description;

	public ChallengeCardRes(Card card, Challenge challenge) {
		this.id = card.getId();
		this.name = card.getName();
		this.photoPath = card.getPhotoPath();
		this.description = card.getDescription();
	}
}
