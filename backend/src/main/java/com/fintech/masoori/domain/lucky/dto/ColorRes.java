package com.fintech.masoori.domain.lucky.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColorRes {
	@Schema(description = "색(Hex)", example = "#FFFFFF")
	private String color;
	@Schema(description = "설명", example = "오늘의 행운의 색은 {color}입니다. 오늘은 ~~를 하면 좋은 일이 생길것 같습니다....")
	private String description;
}
