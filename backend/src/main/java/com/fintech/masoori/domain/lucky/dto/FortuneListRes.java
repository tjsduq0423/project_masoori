package com.fintech.masoori.domain.lucky.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FortuneListRes {

	@Builder.Default
	private List<Fortune> fortuneList = new ArrayList<>();

	@Data
	@Builder
	@AllArgsConstructor
	public static class Fortune{
		@Schema(description = "금전운 이름", example = "행운")
		private String name;
	}
}
