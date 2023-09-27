package com.fintech.masoori.global.rabbitMQ.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeeklyPubService {

	private final RabbitTemplate rabbitTemplate;

}
