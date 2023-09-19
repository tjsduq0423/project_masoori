package com.fintech.masoori.domain.lucky.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FortuneRes {

	@Builder.Default
	private List<Fortune> fortuneList = new ArrayList<>();

	@Data
	@Builder
	@AllArgsConstructor
	public static class Fortune {
		@Schema(description = "금전운 이름", example = "행운")
		private String name;
		@Schema(description = "금전운 이미지 경로", example = "j9b308.p.ssafy.io/img/1234.png")
		private String imagePath;
		@Schema(description = "금전운 설명", example = "이 카드를 뽑은 당신! 오늘은...")
		private String description;

	}
}
