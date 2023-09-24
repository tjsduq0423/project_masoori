package com.fintech.masoori.global.rabbitMQ.controller;

import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.global.rabbitMQ.dto.RabbitMessage;
import com.fintech.masoori.global.rabbitMQ.service.RabbitPublisher;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rabbit")
@RequiredArgsConstructor
public class RabbitmqController {

	private final RabbitPublisher rabbitPublisher;

}
