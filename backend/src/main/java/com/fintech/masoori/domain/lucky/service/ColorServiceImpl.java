package com.fintech.masoori.domain.lucky.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.lucky.dto.ColorRes;
import com.fintech.masoori.domain.lucky.entity.Color;
import com.fintech.masoori.domain.lucky.repository.ColorRepository;
import com.fintech.masoori.global.redis.RedisService;
import com.fintech.masoori.global.util.CalcEndTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ColorServiceImpl implements ColorService {

	private final ColorRepository colorRepository;
	private final RedisService redisService;

	@Override
	public ColorRes selectOneColor(String email) {
		ColorRes color = ColorRes.builder().build();
		//로그인
		if (!email.isEmpty()) {
			Optional<String> userColorOptional = Optional.ofNullable(redisService.getUserColor(email));
			//이미 오늘 조회한 경우
			if (userColorOptional.isPresent()) {
				log.debug("Today already select color");
				String colorName = userColorOptional.get();
				Color findColor = colorRepository.findByColorName(colorName);
				color.setColor(findColor.getColor());
				color.setColorName(findColor.getColorName());
				color.setDescription(findColor.getDescription());
				color.setImagePath(findColor.getImagePath());
				return color;
			}
		}
		//오늘 처음 조회하는 경우, 비로그인인 경우 그냥 진행
		long colorSize = colorRepository.count();
		int idx = (int)(Math.random() * colorSize);
		Page<Color> colorPage = colorRepository.findAll(PageRequest.of(idx, 1));
		if (colorPage.hasContent()) {
			Color temp = colorPage.getContent().get(0);
			color.setColor(temp.getColor());
			color.setColorName(temp.getColorName());
			color.setDescription(temp.getDescription());
			color.setImagePath(temp.getImagePath());
			if (!email.isEmpty()) {
				int limitMinute = CalcEndTime.endMinute();
				redisService.setUserFortune(email, temp.getColorName(), limitMinute);
			}
		}
		return color;
	}
}
