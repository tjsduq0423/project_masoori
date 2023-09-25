package com.fintech.masoori.domain.faq.Dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fintech.masoori.domain.deal.service.DealService;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FaqRes {
	@Builder.Default
	List<Faq> faqList = new ArrayList<>();

	@Data
	@Builder
	@AllArgsConstructor
	public static class Faq{
		@Schema(description = "Faq 제목", example = "타로카드를 만드는 방법은...")
		private String title;
		@Schema(description = "Faq 내용", example = "타로카드를 만들어 보려면 본인인증 후...")
		private String content;
	}
}
