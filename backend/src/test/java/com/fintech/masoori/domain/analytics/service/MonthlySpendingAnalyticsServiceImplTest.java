package com.fintech.masoori.domain.analytics.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;
import com.fintech.masoori.domain.analytics.repository.MonthlySpendingAnalyticsRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.oauth.ProviderType;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
@Rollback
class MonthlySpendingAnalyticsServiceImplTest {

	@Autowired
	UserRepository userRepository;
	@Autowired
	MonthlySpendingAnalyticsService monthlySpendingAnalyticsService;
	@Autowired
	MonthlySpendingAnalyticsRepository monthlySpendingAnalyticsRepository;
	@Autowired
	EntityManager em;

	@Test
	public void selectAll() {
		User user = User.builder().email("ssafy@gmail.com").providerType(ProviderType.LOCAL).build();
		userRepository.save(user);
		MonthlySpendingAnalytics month1 = MonthlySpendingAnalytics.builder()
																   .category("음식")
																   .cost(500000)
																   .analytics("먹어도 먹어도 배가고픈 당신은...")
																   .user(user)
																   .build();
		MonthlySpendingAnalytics month2 = MonthlySpendingAnalytics.builder()
																   .category("쇼핑")
																   .cost(1000000)
																   .analytics("사도 사도 부족한 당신은...")
																   .user(user)
																   .build();
		monthlySpendingAnalyticsRepository.save(month1);
		monthlySpendingAnalyticsRepository.save(month2);

		em.flush();
		em.clear();

		User findUser = userRepository.findByEmail(user.getEmail()).get();
		List<MonthlySpendingAnalytics> monthlySpendingAnalyticsList = findUser.getMonthlySpendingAnalyticsList();
		System.out.println("monthlySpendingAnalyticsList.get(0).getAnalytics() = " + monthlySpendingAnalyticsList.get(0).getAnalytics());
		assertThat(monthlySpendingAnalyticsList.size()).isEqualTo(2);
		assertThat(monthlySpendingAnalyticsList.get(0).getCategory()).isEqualTo("음식");
		assertThat(monthlySpendingAnalyticsList.get(1).getCategory()).isEqualTo("쇼핑");

	}
}
