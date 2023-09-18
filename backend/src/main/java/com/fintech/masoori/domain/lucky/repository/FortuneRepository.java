package com.fintech.masoori.domain.lucky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.lucky.entity.Fortune;

public interface FortuneRepository extends JpaRepository<Fortune, Long> {
}
