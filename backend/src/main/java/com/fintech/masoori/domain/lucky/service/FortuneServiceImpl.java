package com.fintech.masoori.domain.lucky.service;

import java.util.ArrayList;
import java.util.List;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class FortuneServiceImpl implements FortuneService, FortuneUserService {

	private final FortuneRepository fortuneRepository;
	private final UserRepository userRepository;

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
	public FortuneRes.Fortune selectOneFortune() {
		long fortuneSize = fortuneRepository.count();
		int idx = (int)(Math.random() * fortuneSize);
		Page<Fortune> fortunePage = fortuneRepository.findFortunesById(PageRequest.of(idx, 1));
		FortuneRes.Fortune fortune = FortuneRes.Fortune.builder().build();
		if (fortunePage.hasContent()) {
			Fortune temp = fortunePage.getContent().get(0);
			fortune.setName(temp.getName());
			fortune.setImagePath(temp.getImagePath());
			fortune.setDescription(temp.getDescription());
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
