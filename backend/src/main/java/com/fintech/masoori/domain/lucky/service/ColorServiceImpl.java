package com.fintech.masoori.domain.lucky.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
		log.debug("오늘의 색 조회");
		Optional<String> userColorOptional = Optional.ofNullable(redisService.getUserColor(email));
		ColorRes response = ColorRes.builder().build();
		if (userColorOptional.isPresent()) {
			log.debug("이미 오늘의 색을 조회");
			String userColor = userColorOptional.get();
			Color findColor = colorRepository.findDescriptionByColor(userColor);
			response.setColor(findColor.getColor());
			response.setDescription(findColor.getDescription());
		} else {
			log.debug("오늘의 색을 조회");
			long colorSize = colorRepository.count();
			int idx = (int)(Math.random() * colorSize);
			Page<Color> colorPage = colorRepository.findAll(PageRequest.of(idx, 1));
			if(colorPage.hasContent()){
				int limitMinute = CalcEndTime.endMinute();
				Color color = colorPage.getContent().get(0);
				redisService.setUserColor(email, color.getColor(), limitMinute);
				response.setColor(color.getColor());
				response.setDescription(color.getDescription());
			}
		}
		return response;
	}
}
