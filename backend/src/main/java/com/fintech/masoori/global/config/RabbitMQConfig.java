package com.fintech.masoori.global.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RabbitMQConfig {
	@Value("${spring.rabbitmq.host}")
	private String host;

	@Value("${spring.rabbitmq.username}")
	private String username;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Value("${spring.rabbitmq.port}")
	private int port;

	@Value("${rabbitmq.queue.challenge}")
	private String queue1;

	@Value("${rabbitmq.queue.spending}")
	private String queue2;

	@Value("${rabbitmq.queue.analytics}")
	private String queue3;

	@Bean
	public Queue queue1() {
		return new Queue(queue1, true);
	}

	@Bean
	public Queue queue2() {
		return new Queue(queue2, true);
	}

	@Bean
	public Queue queue3() {
		return new Queue(queue3, true);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		return connectionFactory;
	}

	@Bean
	public MessageConverter messageConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule()); // LocalDate 처리를 위한 모듈 등록
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
		return new Jackson2JsonMessageConverter(objectMapper);
	}
}
