package com.fintech.masoori.domain.credit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.credit.dto.CreditCardRes;
import com.fintech.masoori.domain.credit.entity.CreditCard;
import com.fintech.masoori.domain.credit.entity.CreditCardUser;
import com.fintech.masoori.domain.credit.repository.CreditCardRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
	private final UserRepository userRepository;
	private final CreditCardRepository creditCardRepository;

	@Override
	public CreditCardRes selectAll(String userEmail) {
		User user = userRepository.findUserByEmail(userEmail);
		List<CreditCardRes.CreditCard> userCreditCardList = user.getCreditCardUsers().stream().map(e -> {
			CreditCard creditCard = e.getCreditCard();
			String reason = e.getReason();
			CreditCardRes.CreditCard creditCard1 = new CreditCardRes.CreditCard(creditCard);
			creditCard1.setReason(reason);
			return creditCard1;
		}).collect(Collectors.toList());

		return new CreditCardRes(userCreditCardList);
	}

	@Override
	public CreditCard selectOne(Long id) {
		return creditCardRepository.findCreditCardById(id);
	}

	@Override
	public void save(CreditCard creditCard) {
		creditCardRepository.save(creditCard);
	}
}
