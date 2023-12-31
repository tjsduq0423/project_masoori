package com.fintech.masoori.domain.lucky.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.lucky.dto.ColorRes;
import com.fintech.masoori.domain.lucky.entity.Color;
import com.fintech.masoori.domain.lucky.repository.ColorRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.global.oauth.ProviderType;
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

	/*
	유저가 처음 색을 뽑았을 때
	 */
	@Test
	void firstSelectColor() {
		Color color1 = Color.builder().color("#111111").description("1번 색").colorName("빨간색").build();
		Color color2 = Color.builder().color("#222222").description("2번 색").colorName("주황색").build();
		Color color3 = Color.builder().color("#333333").description("3번 색").colorName("노란색").build();
		Color color4 = Color.builder().color("#444444").description("4번 색").colorName("초록색").build();
		Color color5 = Color.builder().color("#555555").description("5번 색").colorName("파란색").build();
		Color color6 = Color.builder().color("#666666").description("6번 색").colorName("보라색").build();
		colorRepository.save(color1);
		colorRepository.save(color2);
		colorRepository.save(color3);
		colorRepository.save(color4);
		colorRepository.save(color5);
		colorRepository.save(color6);
		User user = User.builder()
						.email("test123232@gmail.com")
						.password("123")
						.providerType(ProviderType.LOCAL)
						.build();
		if (redisService.getUserColor(user.getEmail()) == null) {
			//오늘의 색 하나 조회
			ColorRes response = colorService.selectOneColor(user.getEmail());
			log.info("Response : {}, User : {}", response, user.getEmail());
			assertThat(redisService.getUserColor(user.getEmail()).equals(response.getColor()));
			//레디스에서 유저 제거
			redisService.deleteUserColor(user.getEmail());
			assertThat(redisService.getUserColor(user.getEmail()) == null);
		} else {
			redisService.deleteUserColor(user.getEmail());
			fail("이미 Redis에 유저가 존재합니다.");
		}
	}

	/*
	이미 색을 뽑은 유저가 다시 방문했을 때
	 */
	@Test
	void alreadySelectColor() {
		Color color1 = Color.builder().color("#111111").description("1번 색").colorName("빨간색").build();
		Color color2 = Color.builder().color("#222222").description("2번 색").colorName("주황색").build();
		Color color3 = Color.builder().color("#333333").description("3번 색").colorName("노란색").build();
		Color color4 = Color.builder().color("#444444").description("4번 색").colorName("초록색").build();
		Color color5 = Color.builder().color("#555555").description("5번 색").colorName("파란색").build();
		Color color6 = Color.builder().color("#666666").description("6번 색").colorName("보라색").build();
		colorRepository.save(color1);
		colorRepository.save(color2);
		colorRepository.save(color3);
		colorRepository.save(color4);
		colorRepository.save(color5);
		colorRepository.save(color6);
		User user = User.builder()
						.email("test12321321@gmail.com")
						.password("123")
						.providerType(ProviderType.LOCAL)
						.build();
		//유저 방문
		ColorRes response = colorService.selectOneColor(user.getEmail());
		if (redisService.getUserColor(user.getEmail()).equals(response.getColorName())) {
			//redis에 있는 컬러가 저장된 컬러가 맞는지
			String color = redisService.getUserColor(user.getEmail());
			Color findColor = colorRepository.findByColorName(color);
			log.info("Color : {}", findColor);
			assertThat(color.equals(response.getColorName()));
			assertThat(color.equals(findColor.getColorName()));
			redisService.deleteUserColor(user.getEmail());
			assertThat(redisService.getUserColor(user.getEmail()) == null);
		} else {
			fail("redis에 저장되어 있는 color와 유저가 뽑았던 color가 일치하지 않습니다.");
		}
	}
}
