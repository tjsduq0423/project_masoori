package com.fintech.masoori.domain.card.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class BasicCardRes {

	@Builder.Default
	private List<BasicCard> basicCardList = new ArrayList<>();

	@Data
	@Builder
	@Getter
	@AllArgsConstructor
	public static class BasicCard {
		@Schema(description = "카드 기본 정보")
		private Card card;
		@Schema(description = "카드에 속한 소비정보")
		@Builder.Default
		private List<Basic> basicList = new ArrayList<>();
	}

}
