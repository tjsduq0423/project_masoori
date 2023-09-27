package com.fintech.masoori.global.Scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@EnableAsync
public class Scheduler {
	// @Async
	// @Scheduled(fixedRate = 3000)
	// public void scheduleTest() {
	// 	log.info("test1");
	// }
	// @Async
	// @Scheduled(fixedRate = 3000)
	// public void scheduleTest2() {
	// 	log.info("test2");
	// }


}
