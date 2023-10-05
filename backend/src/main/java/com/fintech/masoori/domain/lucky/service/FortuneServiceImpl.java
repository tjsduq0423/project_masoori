package com.fintech.masoori.domain.lucky.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.lucky.dto.FortuneListRes;
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
	public FortuneListRes selectAllFortune() {
		FortuneListRes fortuneListRes = FortuneListRes.builder().build();
		List<Fortune> fortuneList = fortuneRepository.findAll();
		for (Fortune fortune : fortuneList) {
			FortuneRes temp = new FortuneRes(fortune);
			fortuneListRes.getFortuneList().add(temp);
		}
		return fortuneListRes;
	}

	@Override
	@Transactional
	public FortuneRes selectOneFortune(String email) {
		//로그인
		if (!email.isEmpty()) {
			Optional<String> userFortuneOptional = Optional.ofNullable(redisService.getUserFortune(email));
			//이미 오늘 조회한 경우
			if (userFortuneOptional.isPresent()) {
				log.debug("Today already select fortune");
				String fortuneName = userFortuneOptional.get();
				Fortune findFortune = fortuneRepository.findDescriptioneByName(fortuneName);
				return new FortuneRes(findFortune);
			}
		}

		//오늘 처음 조회하는 경우, 비로그인인 경우 그냥 진행
		long fortuneSize = fortuneRepository.count();
		int idx = (int)(Math.random() * fortuneSize);
		Page<Fortune> fortunePage = fortuneRepository.findAll(PageRequest.of(idx, 1));
		if (fortunePage.hasContent()) {
			Fortune temp = fortunePage.getContent().get(0);
			if (!email.isEmpty()) {
				log.debug("Login select fortune");
				int limitMinute = CalcEndTime.endMinute();
				redisService.setUserFortune(email, temp.getName(), limitMinute);
				fortuneRepository.save(temp);
			}
			return new FortuneRes(temp);
		}
		return null;
	}

	@Override
	public FortuneListRes selectAllUserFortune(String email) {
		//유저 정보 조회
		User user = userRepository.findUserByEmail(email);

		//유저가 가지고 있는 fortune을 조회
		List<FortuneUser> fortuneUserList = user.getFortuneUserList();

		//전체 금전운 조회
		FortuneListRes fortuneList = selectAllFortune();

		Map<Long, FortuneRes> fortuneResMap = new HashMap<>();

		for (FortuneRes fortuneRes : fortuneList.getFortuneList()) {
			boolean match = false;
			for (FortuneUser fortuneUser : fortuneUserList) {
				if (fortuneRes.getId().equals(fortuneUser.getFortune().getId())) {
					fortuneResMap.put(fortuneRes.getId(), new FortuneRes((fortuneUser.getFortune())));
					match = true;
				}
			}
			if (!match) {
				fortuneResMap.put(fortuneRes.getId(), null);
			}
		}
		List<FortuneRes> fortuneResList = new ArrayList<>(fortuneResMap.values());
		return FortuneListRes.builder().fortuneList(fortuneResList).build();
	}
}
