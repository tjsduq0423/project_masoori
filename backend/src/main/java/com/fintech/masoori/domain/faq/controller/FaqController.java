package com.fintech.masoori.domain.faq.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.faq.Dto.FaqRes;
import com.fintech.masoori.domain.faq.entity.Faq;
import com.fintech.masoori.domain.faq.service.FaqService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
@Tag(name = "faq", description = "FAQ API")
@RequiredArgsConstructor
public class FaqController {
	private final FaqService faqService;

	@Operation(summary = "FAQ 조회")
	@GetMapping("/faq")
	public ResponseEntity<FaqRes> getFAQ() {
		FaqRes faqList = faqService.getFAQ();
		return ResponseEntity.ok().body(faqList);
	}
}
