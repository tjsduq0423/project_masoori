package com.fintech.masoori.domain.card.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	public void setCard(Card card) {
		this.card = card;
		card.getBasicList().add(this);
	}
}
