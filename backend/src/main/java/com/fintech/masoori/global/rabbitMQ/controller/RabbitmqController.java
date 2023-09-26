package com.fintech.masoori.global.rabbitMQ.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.global.rabbitMQ.dto.RabbitTestMessage;
import com.fintech.masoori.global.rabbitMQ.service.RabbitTestPub;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rabbit")
@RequiredArgsConstructor
public class RabbitmqController {
	private final RabbitTestPub rabbitTestPub;

	@GetMapping("/pub-test")
	public ResponseEntity<?> testPub() {
		for (int i = 0; i < 10; i++) {
			rabbitTestPub.sendMessage(new RabbitTestMessage(String.valueOf(i), "test", "test"));
		}
		return ResponseEntity.ok().build();
	}
}
