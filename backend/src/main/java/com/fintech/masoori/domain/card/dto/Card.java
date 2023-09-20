package com.fintech.masoori.domain.card.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Card {
	@Schema(description = "id(PK)", example = "1")
	private Long id;
	@Schema(description = "카드 이름", example = "카드 이름")
	private String name;
	@Schema(description = "이미지 경로", example = "j9b308.p.ssafy.io/abc.jpg")
	private String imagePath;
	@Schema(description = "카드 설명", example = "이 카드는...")
	private String description;
	@Schema(description = "카드 생성일", example = "2023-09-20")
	private LocalDateTime createdDate;
	@Schema(description = "카드 타입", example = "BASIC, SPECIAL")
	private CardType cardType;
}
