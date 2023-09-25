package com.fintech.masoori.global.rabbitMQ.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
		rabbitTestPub.sendMessage(new RabbitTestMessage("1", "test", "test"));
		return ResponseEntity.ok().build();
	}
}
