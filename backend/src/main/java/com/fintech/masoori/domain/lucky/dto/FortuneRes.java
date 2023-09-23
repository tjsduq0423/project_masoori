package com.fintech.masoori.domain.lucky.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FortuneRes {
	@Schema(description = "금전운 이름", example = "행운")
	private String name;
	@Schema(description = "금전운 이미지 경로", example = "j9b308.p.ssafy.io/img/1234.png")
	private String imagePath;
	@Schema(description = "마녀의 한마디", example = "생각지도 못했던 곳에 갑자기 돈을 쓸 수 있어요.")
	private String summary;
	@Schema(description = "금전운 설명", example = "이 카드를 뽑은 당신! 오늘은...")
	private String description;
}
