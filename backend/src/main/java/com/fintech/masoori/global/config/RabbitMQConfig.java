package com.fintech.masoori.global.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

	@Value("${rabbitmq.queue.name1}")
	private String queue1;

	@Value("${rabbitmq.queue.name2}")
	private String queue2;

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.routing_key.name1}")
	private String routingKey1;

	@Value("${rabbitmq.routing_key.name2}")
	private String routingKey2;

	@Bean
	public Queue queue1() {
		return new Queue(queue1, false);
	}

	@Bean
	public Queue queue2() {
		return new Queue(queue2, false);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(exchange);   // Topic Exchange 타입
	}

	@Bean
	public Binding binding(DirectExchange directExchange, Queue queue1) {
		return BindingBuilder.bind(queue1).to(directExchange).with(routingKey1);
	}

	@Bean
	public Binding binding2(DirectExchange directExchange, Queue queue2) {
		return BindingBuilder.bind(queue2).to(directExchange).with(routingKey2);
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
		return new Jackson2JsonMessageConverter();
	}
}
