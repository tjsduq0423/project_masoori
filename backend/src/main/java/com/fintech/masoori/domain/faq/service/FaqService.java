package com.fintech.masoori.domain.faq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fintech.masoori.domain.faq.Dto.FaqRes;
import com.fintech.masoori.domain.faq.entity.Faq;

@Service
public interface FaqService {

	FaqRes getFAQ();
}
