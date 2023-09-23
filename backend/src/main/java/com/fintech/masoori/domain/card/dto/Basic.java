package com.fintech.masoori.domain.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Basic {
	@Schema(description = "id(PK)", example = "1")
	private Long id;
	@Schema(description = "카테고리 키워드", example = "쇼핑, 식당")
	private String keyword;
	@Schema(description = "총 소비 금액", example = "100000")
	private int totalAmount;
	@Schema(description = "결제 빈도", example = "11")
	private int frequency;

	public Basic(com.fintech.masoori.domain.card.entity.Basic basic) {
		this.id = basic.getId();
		this.keyword = basic.getKeyword();
		this.totalAmount = basic.getTotalAmount();
		this.frequency = basic.getFrequency();
	}
}
