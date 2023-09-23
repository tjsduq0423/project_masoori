package com.fintech.masoori.domain.lucky.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.lucky.dto.FortuneListRes;
import com.fintech.masoori.domain.lucky.dto.FortuneRes;
import com.fintech.masoori.domain.lucky.dto.UserFortuneRes;
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
	public FortuneListRes selectAllFortune() {
		FortuneListRes fortuneListRes = FortuneListRes.builder().build();
		List<String> fortuneNameList = fortuneRepository.findAllOnlyName();
		List<FortuneListRes.Fortune> fortuneResList = new ArrayList<>();
		for (String fortuneName : fortuneNameList) {
			FortuneListRes.Fortune temp = FortuneListRes.Fortune.builder()
										.name(fortuneName)
										.build();
			fortuneResList.add(temp);
		}
		fortuneListRes.setFortuneList(fortuneResList);
		return fortuneListRes;
	}

	@Override
	public FortuneRes selectOneFortune(String email) {
		FortuneRes fortune = FortuneRes.builder().build();
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
				fortune.setSummary(findFortune.getSummary());
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
			fortune.setSummary(temp.getSummary());
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
	public UserFortuneRes selectAllUserFortune(String email) {
		User user = userRepository.findUserByEmail(email);
		List<FortuneUser> fortuneUserList = user.getFortuneUserList();
		UserFortuneRes fortuneRes = UserFortuneRes.builder().build();
		List<FortuneRes> fortuneList = new ArrayList<>();
		for (FortuneUser f : fortuneUserList) {
			Fortune fortune = f.getFortune();
			fortuneList.add(FortuneRes.builder()
									  .name(fortune.getName())
									  .imagePath(fortune.getImagePath())
									  .summary(fortune.getSummary())
									  .description(fortune.getDescription())
									  .build());
		}
		fortuneRes.setFortuneList(fortuneList);
		return fortuneRes;
	}
}
