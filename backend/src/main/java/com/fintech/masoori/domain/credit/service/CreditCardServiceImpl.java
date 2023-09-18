package com.fintech.masoori.domain.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fintech.masoori.domain.credit.dto.CreditCardRes;
import com.fintech.masoori.domain.credit.entity.CreditCardUser;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
	private UserRepository userRepository;

	@Override
	public List<CreditCardRes.CreditCard> selectAll(String userEmail) {
		User user = userRepository.findUserByEmail(userEmail);
		return user.getCreditCardUsers()
		           .stream()
		           .map(CreditCardUser::getCreditCard)
		           .map(CreditCardRes.CreditCard::new)
		           .toList();
	}
}
