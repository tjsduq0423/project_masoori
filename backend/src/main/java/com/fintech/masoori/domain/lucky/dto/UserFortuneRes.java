package com.fintech.masoori.domain.lucky.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFortuneRes {

	@Builder.Default
	private List<FortuneRes> fortuneList = new ArrayList<>();


}
