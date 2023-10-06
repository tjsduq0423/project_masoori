package com.fintech.masoori.domain.lucky.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FortuneListRes {

	@Builder.Default
	private List<FortuneRes> fortuneList = new ArrayList<>();

}
