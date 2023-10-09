package com.fintech.masoori.domain.analytics.entity;

import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.global.util.BaseTimeEntity;

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
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "monthly_spending_analytics")
@ToString(of = {"category", "cost", "analytics"})
public class MonthlySpendingAnalytics extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "monthly_spending_analytics_id", nullable = false)
	private Long id;

	@Column(name = "category")
	private String category;

	@Column(name = "cost")
	private Integer cost;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
