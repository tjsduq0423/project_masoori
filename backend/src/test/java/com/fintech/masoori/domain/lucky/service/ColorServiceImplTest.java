package com.fintech.masoori.domain.lucky.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.lucky.dto.ColorRes;
import com.fintech.masoori.domain.lucky.entity.Color;
import com.fintech.masoori.domain.lucky.repository.ColorRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.global.redis.RedisService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
class ColorServiceImplTest {
	@Autowired
	ColorService colorService;
	@Autowired
	ColorRepository colorRepository;
	@Autowired
	RedisService redisService;

	@Test
	void firstSelectColor() {
		Color color1 = Color.builder().color("#111111").description("1번 색").build();
		Color color2 = Color.builder().color("#222222").description("2번 색").build();
		Color color3 = Color.builder().color("#333333").description("3번 색").build();
		Color color4 = Color.builder().color("#444444").description("4번 색").build();
		Color color5 = Color.builder().color("#555555").description("5번 색").build();
		Color color6 = Color.builder().color("#666666").description("6번 색").build();
		colorRepository.save(color1);
		colorRepository.save(color2);
		colorRepository.save(color3);
		colorRepository.save(color4);
		colorRepository.save(color5);
		colorRepository.save(color6);
		User user = User.builder().email("test@gmail.com").password("123").build();
		Optional<String> userColorOptional = Optional.ofNullable(redisService.getUserColor(user.getEmail()));
		if (userColorOptional.isPresent()) {
			log.info("Already user select color");
			ColorRes response = colorService.selectOneColor(user.getEmail());
		} else {
			ColorRes response = colorService.selectOneColor(user.getEmail());
			log.info("Response : {}", response);
		}

	}
}
