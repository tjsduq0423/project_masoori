package com.fintech.masoori.domain.deal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.deal.entity.Deal;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
