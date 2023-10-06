package com.fintech.masoori.domain.card.entity;

import java.time.LocalDateTime;

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
@Table(name = "challenge")
@ToString(of = {"id", "isSuccess", "achievementCondition", "startTime", "endTime"})
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

	@Column(name = "achievement_condition", length = 25)
	private String achievementCondition;

	@Column(name = "start_time")
	private LocalDateTime startTime;

	@Column(name = "end_time")
	private LocalDateTime endTime;

	public void setCard(Card card) {
		this.card = card;
		card.getChallengeList().add(this);
	}
}
