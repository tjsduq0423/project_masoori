package com.fintech.masoori.domain.credit.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardRes {
	private List<CreditCard> creditCardList;

	@Data
	@AllArgsConstructor
	private static class CreditCard {
		private String name;
		private String company;
		private String domestic;
		private String overseas;
		private String condition;
		private String brand;
		private String imagePath;
		private String registerPath;
		private String reason;
		private List<Benefit> benefitList;
	}

	@Data
	@AllArgsConstructor
	private static class Benefit {
		private String title;
		private String description;
		private String detailDescription;
	}

}
