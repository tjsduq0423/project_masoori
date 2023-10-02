package com.fintech.masoori.global.sse.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fintech.masoori.global.sse.repository.EmitterRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60; // 60초
	private final EmitterRepository emitterRepository;

	public SseEmitter subscribe(String email) {
		SseEmitter emitter = createEmitter(email);
		sendToClient(email, "EventStream Created. [email=" + email + "]");
		return emitter;
	}

	// 사용자 이메일과 이벤트를 적재해서 알림을 보내는 로직
	public void notify(String email, Object event) {
		sendToClient(email, event);
	}

	private void sendToClient(String email, Object data) {
		SseEmitter emitter = emitterRepository.get(email);
		if (emitter != null) {
			try {
				emitter.send(SseEmitter.event().id(email).name("sse").data(data));
			} catch (IOException exception) {
				log.info("sse exception : {}", exception.getMessage());
				emitterRepository.deleteByEmail(email);
				emitter.completeWithError(exception);
			}
		}
	}

	private SseEmitter createEmitter(String email) {
		SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
		emitterRepository.save(email, emitter);
		// Emitter가 완료될 때(모든 데이터가 성공적으로 전송된 상태) Emitter를 삭제한다.
		emitter.onCompletion(() -> emitterRepository.deleteByEmail(email));

		// Emitter가 타임아웃 되었을 때(지정된 시간동안 어떠한 이벤트도 전송되지 않았을 때) Emitter를 삭제한다.
		emitter.onTimeout(() -> emitterRepository.deleteByEmail(email));
		return emitter;
	}
}
