package com.fintech.masoori.domain.deal.entity;

import java.time.LocalDateTime;

import com.fintech.masoori.domain.user.entity.User;

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

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "deal")
public class Deal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "deal_id", nullable = false)
	private Long id;

	@Column(name = "date")
	private LocalDateTime date;

	@Column(name = "content")
	private String content;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "deal_place_name")
	private String dealPlaceName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;


}
