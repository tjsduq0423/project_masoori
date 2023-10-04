package com.fintech.masoori.domain.card.entity;

import java.util.ArrayList;
import java.util.List;

import com.fintech.masoori.domain.card.dto.CardType;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.global.util.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "card")
@ToString(of = {"id", "name", "imagePath", "description", "cardType"})
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

	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "card_type")
	@Enumerated(EnumType.STRING)
	private CardType cardType;

	@OneToMany(mappedBy = "card")
	@Builder.Default
	private List<Challenge> challengeList = new ArrayList<>();

	@OneToMany(mappedBy = "card")
	@Builder.Default
	private List<Basic> basicList = new ArrayList<>();

	public void addChallengeList(Challenge challenge) {
		this.challengeList.add(challenge);
		challenge.setCard(this);
	}

	public void addBasicList(Basic basic) {
		this.basicList.add(basic);
		basic.setCard(this);
	}

	public void setUser(User user) {
		this.user = user;
		user.getCardList().add(this);
	}

	public void cardUpdate(String name, String imagePath, String description) {
		this.name = name;
		this.imagePath = imagePath;
		this.description = description;
	}
}
