package com.fintech.masoori.domain.card.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Random;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.card.dto.Basic;
import com.fintech.masoori.domain.card.dto.BasicCardRes;
import com.fintech.masoori.domain.card.dto.CardType;
import com.fintech.masoori.domain.card.dto.Challenge;
import com.fintech.masoori.domain.card.dto.ChallengeCardRes;
import com.fintech.masoori.domain.card.dto.UserCardListRes;
import com.fintech.masoori.domain.card.entity.Card;
import com.fintech.masoori.domain.card.exception.CardNotFound;
import com.fintech.masoori.domain.card.repository.BasicRepository;
import com.fintech.masoori.domain.card.repository.CardRepository;
import com.fintech.masoori.domain.card.repository.ChallengeRepository;
import com.fintech.masoori.domain.deal.service.DealService;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.exception.UserNotFoundException;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.rabbitMQ.dto.ChallengeRequestMessage;
import com.fintech.masoori.global.rabbitMQ.dto.GeneratedSpending;
import com.fintech.masoori.global.rabbitMQ.dto.GeneratedSpendingCard;
import com.fintech.masoori.global.rabbitMQ.dto.SpendingRequestMessage;
import com.fintech.masoori.global.rabbitMQ.dto.Transaction;
import com.fintech.masoori.global.rabbitMQ.service.ChallengePubService;
import com.fintech.masoori.global.rabbitMQ.service.SpendingPubService;
import com.fintech.masoori.global.util.CalcDate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService {
	private final String[] NAME_LIST = {"Fool", "Magician", "High Priestess", "Empress", "Emperor", "Hierophant",
		"Lovers", "Chariot", "Strength", "Hermit", "Wheel of Fortune", "Justice", "Hanged Man", "Death", "Temperance",
		"Devil", "Tower", "Star", "Moon", "Sun", "Judgment", "World", "Ace of Wands", "Two of Wands", "Three of Wands",
		"Four of Wands", "Five of Wands", "Six of Wands", "Seven of Wands", "Eight of Wands"};
	private final String[] VERSE_LIST = {"Conquering", "Rising", "Falling", "Leaning", "Fleeing", "Despairing",
		"Rejoicing", "Exploring", "Transforming", "Discovering", "Balancing"};
	private final SpendingPubService spendingPubService;
	private final ChallengePubService challengePubService;
	private final CardRepository cardRepository;
	private final UserRepository userRepository;
	private final DealService dealService;
	private final ChallengeRepository ChallengeRepository;
	private final BasicRepository basicRepository;

	@Override
	public UserCardListRes selectRangeBasicCard(String email, LocalDateTime start, LocalDateTime end) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다."));

		CalcDate.StartEndDate calcDate = CalcDate.calcDate(start, end);

		List<Card> cardList = cardRepository.findRangeCard(user.getId(), CardType.BASIC, calcDate.getStartDate(),
			calcDate.getEndDate());

		List<UserCardListRes.UserCard> userBasicCardList = cardList.stream()
		                                                           .map(card -> UserCardListRes.UserCard.builder()
		                                                                                                .name(
			                                                                                                card.getName())
		                                                                                                .cardType(
			                                                                                                card.getCardType())
		                                                                                                .id(card.getId())
		                                                                                                .createdDate(
			                                                                                                card.getCreatedDate())
		                                                                                                .imagePath(
			                                                                                                card.getImagePath())
		                                                                                                .build())
		                                                           .toList();

		return UserCardListRes.builder().userCardList(userBasicCardList).build();
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
	public UserCardListRes selectRangeChallengeCard(String email, LocalDateTime start, LocalDateTime end) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다."));

		CalcDate.StartEndDate calcDate = CalcDate.calcDate(start, end);

		List<Card> cardList = cardRepository.findRangeCard(user.getId(), CardType.SPECIAL, calcDate.getStartDate(),
			calcDate.getEndDate());

		List<UserCardListRes.UserCard> userChallengeCardList = cardList.stream()
		                                                               .map(card -> UserCardListRes.UserCard.builder()
		                                                                                                    .name(
			                                                                                                    card.getName())
		                                                                                                    .cardType(
			                                                                                                    card.getCardType())
		                                                                                                    .id(card.getId())
		                                                                                                    .createdDate(
			                                                                                                    card.getCreatedDate())
		                                                                                                    .imagePath(
			                                                                                                    card.getImagePath())
		                                                                                                    .build())
		                                                               .toList();

		return UserCardListRes.builder().userCardList(userChallengeCardList).build();
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

	@Override
	@Transactional
	public void registerSpendingCard(GeneratedSpendingCard generatedSpendingCard) {
		Card card = cardRepository.findById(generatedSpendingCard.getCardId())
		                          .orElseThrow(() -> new CardNotFound("Card Is Not Found"));
		card.cardUpdate(generatedSpendingCard.getName(), generatedSpendingCard.getImagePath(),
			generatedSpendingCard.getDescription());

		cardRepository.save(card);

		List<GeneratedSpending> spendings = generatedSpendingCard.getSpendings();
		for (GeneratedSpending s : spendings) {
			com.fintech.masoori.domain.card.entity.Basic basic = com.fintech.masoori.domain.card.entity.Basic.builder()
			                                                                                                 .keyword(
				                                                                                                 s.getKeyword())
			                                                                                                 .totalAmount(
				                                                                                                 s.getTotalAmount())
			                                                                                                 .frequency(
				                                                                                                 s.getFrequency())
			                                                                                                 .build();
			basicRepository.save(basic);
			basic.setCard(card);
		}
	}

	@Override
	@Transactional
	public void createSpendingCard(String email) {
		// 사용자 찾기.
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Is Not Found"));
		Card card = Card.builder().cardType(CardType.BASIC).user(user).build();
		CalcDate.StartEndDate startEndDate = CalcDate.calcLastWeek();
		List<Transaction> transactionList = dealService.findDealsByUserAndDateGreaterThanAndDateLessThan(user,
			startEndDate.getStartDate(), startEndDate.getEndDate());
		// 소비 카드 생성 중인지 저장.
		spendingPubService.sendMessage(
			SpendingRequestMessage.builder().cardId(card.getId()).userWeeklyTransactionList(transactionList).build());
		cardRepository.save(card);
	}

	@Override
	@Transactional
	public void createSpendingCard(String email, LocalDateTime date) {
		// 사용자 찾기.
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Is Not Found"));
		Card card = Card.builder().cardType(CardType.BASIC).user(user).build();
		cardRepository.save(card);
		card.setLocalDateTime(date);
		CalcDate.StartEndDate startEndDate = CalcDate.calcLastWeek(date);
		List<Transaction> transactionList = dealService.findDealsByUserAndDateGreaterThanAndDateLessThan(user,
			startEndDate.getStartDate(), startEndDate.getEndDate());
		// 소비 카드 생성 중인지 저장.
		spendingPubService.sendMessage(
			SpendingRequestMessage.builder().cardId(card.getId()).userWeeklyTransactionList(transactionList).build());
	}

	@Override
	@Transactional
	public void registerChallengeCardImage(String imgPath, Long cardId) {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new CardNotFound("Card Is Not Found"));
		card.updateImgPath(imgPath);
	}

	@Override
	@Transactional
	public void addChallenge(Long userId, String achievementCondition) {
		CalcDate.StartEndDate startEndDate = CalcDate.calcRecentWeek();
		Card card = cardRepository.findRecentCard(userId, CardType.SPECIAL, startEndDate.getStartDate(),
			startEndDate.getEndDate());
		com.fintech.masoori.domain.card.entity.Challenge challenge = com.fintech.masoori.domain.card.entity.Challenge.builder()
		                                                                                                             .achievementCondition(
			                                                                                                             achievementCondition)
		                                                                                                             .startTime(
			                                                                                                             startEndDate.getStartDate())
		                                                                                                             .endTime(
			                                                                                                             startEndDate.getEndDate())
		                                                                                                             .isSuccess(
			                                                                                                             false)
		                                                                                                             .build();
		ChallengeRepository.save(challenge);
		card.updateChallengeIdx(card.getChallengeIdx() + 1);
		challenge.setCard(card);
	}

	@Override
	@Transactional
	public void addChallenge(Long userId, String achievementCondition, LocalDateTime date) {
		CalcDate.StartEndDate startEndDate = CalcDate.calcLastWeek(date);
		Card card = cardRepository.findRecentCard(userId, CardType.SPECIAL, startEndDate.getStartDate(),
			startEndDate.getEndDate());

		com.fintech.masoori.domain.card.entity.Challenge challenge = com.fintech.masoori.domain.card.entity.Challenge.builder()
		                                                                                                             .achievementCondition(
			                                                                                                             achievementCondition)
		                                                                                                             .startTime(
			                                                                                                             startEndDate.getStartDate())
		                                                                                                             .endTime(
			                                                                                                             startEndDate.getEndDate())
		                                                                                                             .isSuccess(
			                                                                                                             false)
		                                                                                                             .build();
		ChallengeRepository.save(challenge);
		card.updateChallengeIdx(card.getChallengeIdx() + 1);
		challenge.setCard(card);
	}

	@Override
	@Transactional
	public void createChallengeCard(String email) {
		// 사용자 찾기.
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Is Not Found"));
		// 카드 이름 정하기
		// 랜덤한 인덱스 생성
		Random random = new Random();
		// 이름과
		String n = NAME_LIST[random.nextInt(NAME_LIST.length)];
		String v = VERSE_LIST[random.nextInt(VERSE_LIST.length)];
		String cardName = v + " " + n;
		Card card = Card.builder()
		                .cardType(CardType.SPECIAL)
		                .user(user)
		                .description("")
		                .name(cardName)
		                .challengeIdx(0)
		                .build();
		// 이미지 생성 요청.
		cardRepository.save(card);
		challengePubService.sendMessage(ChallengeRequestMessage.builder().cardId(card.getId()).name(cardName).build());
	}

	@Override
	@Transactional
	public void createChallengeCard(String email, LocalDateTime date) {
		// 사용자 찾기.
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Is Not Found"));
		// 카드 이름 정하기
		// 랜덤한 인덱스 생성
		Random random = new Random();
		// 이름과
		String n = NAME_LIST[random.nextInt(NAME_LIST.length)];
		String v = VERSE_LIST[random.nextInt(VERSE_LIST.length)];
		String cardName = v + " " + n;
		Card card = Card.builder()
		                .cardType(CardType.SPECIAL)
		                .user(user)
		                .description("")
		                .name(cardName)
		                .challengeIdx(0)
		                .build();
		// 이미지 생성 요청.
		cardRepository.save(card);
		card.setLocalDateTime(date);
		challengePubService.sendMessage(ChallengeRequestMessage.builder().cardId(card.getId()).name(cardName).build());
	}

	@Override
	public BasicCardRes.BasicCard selectUserRecentBasicCard(String email, LocalDateTime time) {
		User user = userRepository.findUserByEmail(email);
		LocalDateTime monday = time.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDateTime sunday = time.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		LocalDateTime startDate = LocalDateTime.of(monday.getYear(), monday.getMonth(), monday.getDayOfMonth(), 0, 0,
			0);
		LocalDateTime endDate = LocalDateTime.of(sunday.getYear(), sunday.getMonth(), sunday.getDayOfMonth(), 23, 59,
			59);
		Card recentCard = cardRepository.findRecentCard(user.getId(), CardType.BASIC, startDate, endDate);
		if (recentCard == null) {
			return null;
		}
		return BasicCardRes.BasicCard.builder().card(new com.fintech.masoori.domain.card.dto.Card(recentCard)).build();
	}

	@Override
	public Long findTopByUserIdRecentlyChallengeCard(String email) {
		User loginUser = userRepository.findUserByEmail(email);
		LocalDateTime now = LocalDateTime.now();
		Card recentlyChallengeCard = cardRepository.findTopByUserIdRecentlyChallengeCard(loginUser.getId(),
			CardType.SPECIAL, now, PageRequest.of(0, 1));
		if (recentlyChallengeCard == null)
			return null;
		return recentlyChallengeCard.getId();
	}

	@Override
	public void updateUserProfileImage(String email, Long id) {
		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			throw new UserNotFoundException("User Is Not Found");
		}
		Card card = cardRepository.findCardByUserIdAndId(user.getId(), id);
		if (card == null) {
			throw new CardNotFound("Card not found");
		}
		userRepository.updateUserProfileImage(card.getImagePath(), user.getEmail());
	}
}

