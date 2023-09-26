package com.fintech.masoori.domain.credit.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardRes {
	private List<CreditCard> creditCardList;

	@Data
	@Builder
	@AllArgsConstructor
	public static class CreditCard {
		private String name;
		private String company;
		private String domestic;
		private String overseas;
		private String condition;
		private List<String> brandList;
		private String imagePath;
		private String imageAttr;
		private String registerPath;
		private String reason;
		private List<CreditCardRes.Benefit> benefitList;

		public CreditCard(com.fintech.masoori.domain.credit.entity.CreditCard creditCard) {
			this.name = creditCard.getName();
			this.company = creditCard.getCompany();
			this.domestic = creditCard.getDomestic();
			this.overseas = creditCard.getOverseas();
			this.condition = creditCard.getCondition();
			this.brandList = creditCard.getBrands();
			this.imagePath = creditCard.getImagePath();
			this.imageAttr = creditCard.getImgAttr();
			this.registerPath = creditCard.getRegisterPath();
			this.benefitList = creditCard.getBenefits().stream().map(Benefit::new).toList();
		}
	}

	@Data
	private static class Benefit {
		private String title;
		private String description;
		private String detailDescription;

		public Benefit(com.fintech.masoori.domain.credit.entity.Benefit benefit) {
			this.title = benefit.getTitle();
			this.description = benefit.getDescription();
			this.detailDescription = benefit.getDetailDescription();
		}
	}

}
