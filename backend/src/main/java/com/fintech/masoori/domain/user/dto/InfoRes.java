package com.fintech.masoori.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InfoRes {
	@Schema(description = "대표카드 이미지 경로 img src", example = "http://gibhub.io/dshjflhl123898d.png")
	private String imagePath;

	@Schema(description = "결제 연동 여부", example = "true or false")
	private Boolean isPaymentInfoLinked;

	@Schema(description = "문자 알림 여부(카드 생성 알람 입니다.)", example = "true or false")
	private Boolean smsAlarm;

	@Schema(description = "일간 소비량", example = "40000")
	private Integer dailySpending;

	@Schema(description = "주간 소비량", example = "430200")
	private Integer weeklySpending;

	@Schema(description = "월간 소비량", example = "1300240")
	private Integer monthlySpending;
}
