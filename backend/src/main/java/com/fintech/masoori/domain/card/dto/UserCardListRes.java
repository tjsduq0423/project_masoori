package com.fintech.masoori.domain.card.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCardListRes {

	@Builder.Default
	List<UserCard> userCardList = new ArrayList<>();

	@Data
	@Builder
	@AllArgsConstructor
	public static class UserCard {
		@Schema(description = "id(PK)", example = "1")
		private Long id;
		@Schema(description = "카드 이름", example = "카드 이름")
		private String name;
		@Schema(description = "이미지 경로", example = "http://j9b308.p.ssafy.io/abc.jpg")
		private String imagePath;
		@Schema(description = "카드 생성일", example = "2023-09-26T07:42:34.76")
		private LocalDateTime createdDate;
		@Schema(description = "카드 타입", example = "BASIC, SPECIAL")
		private CardType cardType;
	}
}
