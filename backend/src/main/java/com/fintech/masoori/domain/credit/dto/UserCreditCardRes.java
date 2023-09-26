package com.fintech.masoori.domain.credit.dto;

import java.util.ArrayList;
import java.util.List;

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
		private Long id;
		private String name;
		private String company;
		private String imagePath;
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
