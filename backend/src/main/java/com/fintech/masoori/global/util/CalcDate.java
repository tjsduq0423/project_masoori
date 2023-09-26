package com.fintech.masoori.global.util;

import java.time.LocalDateTime;
import java.time.YearMonth;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class CalcDate {

	@Data
	@Getter
	@Builder
	public static class StartEndDate {
		private LocalDateTime startDate;
		private LocalDateTime endDate;
	}

	public static StartEndDate calcDate(LocalDateTime start, LocalDateTime end) {
		return StartEndDate.builder()
						   .startDate(LocalDateTime.of(start.getYear(), start.getMonth(), 1, 0, 0, 0))
						   .endDate(LocalDateTime.of(end.getYear(), end.getMonth(),
							   YearMonth.from(end).atEndOfMonth().getDayOfMonth(), 23, 59, 59))
						   .build();
	}
}
