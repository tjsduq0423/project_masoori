package com.fintech.masoori.domain.lucky.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.lucky.dto.FortuneRes;
import com.fintech.masoori.domain.lucky.entity.Fortune;
import com.fintech.masoori.domain.lucky.entity.FortuneUser;
import com.fintech.masoori.domain.lucky.repository.FortuneRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.redis.RedisService;
import com.fintech.masoori.global.util.CalcEndTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class FortuneServiceImpl implements FortuneService, FortuneUserService {

	private final FortuneRepository fortuneRepository;
	private final UserRepository userRepository;
	private final RedisService redisService;

	@Override
	public FortuneRes selectAllFortune() {
		FortuneRes fortuneRes = FortuneRes.builder().build();
		List<Fortune> fortuneList = fortuneRepository.findAll();
		List<FortuneRes.Fortune> fortuneResList = new ArrayList<>();
		for (Fortune fortune : fortuneList) {
			FortuneRes.Fortune temp = FortuneRes.Fortune.builder()
														.name(fortune.getName())
														.imagePath(fortune.getImagePath())
														.description(fortune.getDescription())
														.build();
			fortuneResList.add(temp);
		}
		fortuneRes.setFortuneList(fortuneResList);
		return fortuneRes;
	}

	@Override
	public FortuneRes.Fortune selectOneFortune(String email) {
		FortuneRes.Fortune fortune = FortuneRes.Fortune.builder().build();
		//로그인
		if (!email.isEmpty()) {
			Optional<String> userFortuneOptional = Optional.ofNullable(redisService.getUserFortune(email));
			//이미 오늘 조회한 경우
			if (userFortuneOptional.isPresent()) {
				log.debug("Today already select fortune");
				String fortuneName = userFortuneOptional.get();
				Fortune findFortune = fortuneRepository.findDescriptioneByName(fortuneName);
				fortune.setName(findFortune.getName());
				fortune.setImagePath(findFortune.getImagePath());
				fortune.setDescription(findFortune.getDescription());
				return fortune;

			}
		}
		//오늘 처음 조회하는 경우, 비로그인인 경우 그냥 진행
		long fortuneSize = fortuneRepository.count();
		int idx = (int)(Math.random() * fortuneSize);
		Page<Fortune> fortunePage = fortuneRepository.findAll(PageRequest.of(idx, 1));
		if (fortunePage.hasContent()) {
			Fortune temp = fortunePage.getContent().get(0);
			fortune.setName(temp.getName());
			fortune.setImagePath(temp.getImagePath());
			fortune.setDescription(temp.getDescription());
			if (!email.isEmpty()) {
				log.debug("Login select fortune");
				int limitMinute = CalcEndTime.endMinute();
				redisService.setUserFortune(email, temp.getName(), limitMinute);
			}
		}
		return fortune;
	}

	@Override
	public FortuneRes selectAllUserFortune(String email) {
		User user = userRepository.findUserByEmail(email);
		List<FortuneUser> fortuneUserList = user.getFortuneUserList();
		FortuneRes fortuneRes = FortuneRes.builder().build();
		List<FortuneRes.Fortune> fortuneList = new ArrayList<>();
		for (FortuneUser f : fortuneUserList) {
			Fortune fortune = f.getFortune();
			fortuneList.add(FortuneRes.Fortune.builder()
											  .name(fortune.getName())
											  .imagePath(fortune.getImagePath())
											  .description(fortune.getDescription())
											  .build());
		}
		fortuneRes.setFortuneList(fortuneList);
		return fortuneRes;
	}
}
