package com.fintech.masoori.domain.card.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.card.dto.Basic;
import com.fintech.masoori.domain.card.dto.BasicCardRes;
import com.fintech.masoori.domain.card.dto.CardType;
import com.fintech.masoori.domain.card.dto.Challenge;
import com.fintech.masoori.domain.card.dto.ChallengeCardRes;
import com.fintech.masoori.domain.card.entity.Card;
import com.fintech.masoori.domain.card.repository.CardRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.exception.UserNotFoundException;
import com.fintech.masoori.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService {

	private final CardRepository cardRepository;
	private final UserRepository userRepository;

	@Override
	public BasicCardRes selectRangeBasicCard(String email, LocalDateTime start, LocalDateTime end) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다."));

		LocalDateTime startDate = LocalDateTime.of(start.getYear(), start.getMonth(), 1, 0, 0);
		LocalDateTime endDate = LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth(), 23, 59);

		List<Card> cardList = cardRepository.findRangeCard(user.getId(), CardType.BASIC, startDate, endDate);

		List<BasicCardRes.BasicCard> basicCardList = cardList.stream()
															 .map(card -> BasicCardRes.BasicCard.builder()
																								.card(
																									new com.fintech.masoori.domain.card.dto.Card(
																										card))
																								.basicList(
																									card.getBasicList()
																										.stream()
																										.map(Basic::new)
																										.toList())
																								.build()).toList();

		return BasicCardRes.builder().basicCardList(basicCardList).build();
	}

	@Override
	public BasicCardRes.BasicCard selectBasicCard(String email, long cardId) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다."));
		Card card = cardRepository.findCard(user.getId(), CardType.BASIC, cardId);
		return BasicCardRes.BasicCard.builder()
									 .card(new com.fintech.masoori.domain.card.dto.Card(card))
									 .basicList(card.getBasicList().stream().map(Basic::new).toList())
									 .build();
	}

	@Override
	public ChallengeCardRes selectRangeChallengeCard(String email, LocalDateTime start, LocalDateTime end) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다."));

		LocalDateTime startDate = LocalDateTime.of(start.getYear(), start.getMonth(), 1, 0, 0);
		LocalDateTime endDate = LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth(), 23, 59);

		List<Card> cardList = cardRepository.findRangeCard(user.getId(), CardType.SPECIAL, startDate, endDate);

		List<ChallengeCardRes.ChallengeCard> challengeCardList = cardList.stream()
																		 .map(
																			 card -> ChallengeCardRes.ChallengeCard.builder()
																												   .card(
																													   new com.fintech.masoori.domain.card.dto.Card(
																														   card))
																												   .challengeList(
																													   card.getChallengeList()
																														   .stream()
																														   .map(
																															   Challenge::new)
																														   .toList())
																												   .build())
																		 .toList();

		return ChallengeCardRes.builder().challengeCardList(challengeCardList).build();
	}

	@Override
	public ChallengeCardRes.ChallengeCard selectChallengeCard(String email, long cardId) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다."));
		Card card = cardRepository.findCard(user.getId(), CardType.SPECIAL, cardId);
		return ChallengeCardRes.ChallengeCard.builder()
											 .card(new com.fintech.masoori.domain.card.dto.Card(card))
											 .challengeList(
												 card.getChallengeList().stream().map(Challenge::new).toList())
											 .build();
	}
}
