package com.fintech.masoori.domain.credit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "benefits")
public class Benefit {
	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "detail_description", columnDefinition = "TEXT")
	private String detailDescription;
}
