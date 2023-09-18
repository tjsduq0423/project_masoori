package com.fintech.masoori.domain.faq.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.faq.entity.Faq;
import com.fintech.masoori.domain.faq.repository.FaqRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

	private final FaqRepository faqRepository;

	@Override
	public List<Faq> getFAQ() {
		return faqRepository.findAll();
	}
}
