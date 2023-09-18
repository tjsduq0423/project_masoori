package com.fintech.masoori.domain.credit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/creditcard")
@Tag(name = "creaditCard", description = "신용카드 API")
@RequiredArgsConstructor
public class CreditCardController {

}
