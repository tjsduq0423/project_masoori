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
