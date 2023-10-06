package com.fintech.masoori.domain.credit.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserCreditCardRes {
	@Builder.Default
	private List<UserCreditCard> userCreditCardList = new ArrayList<>();

	@Data
	@Builder
	@AllArgsConstructor
	public static class UserCreditCard {
		@Schema(description = "CreditCard Id", example = "1")
		private Long id;
		@Schema(description = "CreditCard 이름", example = "신한XX카드")
		private String name;
		@Schema(description = "CreditCard 회사", example = "신한카드")
		private String company;
		@Schema(description = "CreditCard 이미지 경로", example = "http://~~~/image.png")
		private String imagePath;
		@Schema(description = "CreditCard 신청 경로", example = "https://카드회사 경로")
		private String registerPath;

		public UserCreditCard(com.fintech.masoori.domain.credit.entity.CreditCard creditCard) {
			this.id = creditCard.getId();
			this.name = creditCard.getName();
			this.company = creditCard.getCompany();
			this.imagePath = creditCard.getImagePath();
			this.registerPath = creditCard.getRegisterPath();
		}
	}
}
