package com.fintech.masoori.global.sse.controller;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fintech.masoori.global.sse.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/sse")
@RequiredArgsConstructor
@Tag(name = "SSE 연동 api", description = "사용자 알림을 위한 연결 api 받게되는 msg에 따라 챌린지, 타로카드를 업데이트하면된다.")
public class SSEController {
	private final NotificationService notificationService;

	@Operation(summary = "카드 fetch할 까 말 까?  관련된 데이터받는 API", description = "sse구독을 통해 message를 받는다.")
	@GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter subscribe(Principal principal) {
		log.info("email :  {}", principal.getName());
		return notificationService.subscribe(principal.getName());
	}

	@Operation(summary = "카드 데이터 구독 테스트 용 API", description = "서버 to 클라이언트 test 문자열 날림")
	@PostMapping("/send-data")
	public void sendData(Principal principal) {
		notificationService.notify(principal.getName(), "TEST DATA");
	}

}
