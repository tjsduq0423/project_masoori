package com.fintech.masoori.global.util;

import java.time.LocalTime;

public class CalcEndTime {
	public static int endMinute() {
		LocalTime now = LocalTime.now();
		return (23 - now.getHour()) * 60 + (59 - now.getMinute());
	}
}
