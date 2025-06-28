package com.kaba4cow.futuresscreenerbot.service.domain.event;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.repository.EventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventCleanUpService {

	private final EventRepository eventRepository;

	@Scheduled(fixedDelay = 10L, timeUnit = TimeUnit.MINUTES)
	public void deleteExpiredEvents() {
		long expiredEvents = eventRepository.deleteExpiredEvents(LocalDateTime.now().minusHours(24L));
		log.info("Deleted {} expired events", expiredEvents);
	}

}
