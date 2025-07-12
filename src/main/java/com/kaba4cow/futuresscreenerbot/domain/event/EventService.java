package com.kaba4cow.futuresscreenerbot.domain.event;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.service.notification.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventService {

	private final EventRepository eventRepository;

	private final NotificationService notificationService;

	public Event registerEvent(EventSignature signature, Double value) {
		Event event = new Event();
		event.setSignature(signature);
		event.setValue(value);
		Event savedEvent = eventRepository.save(event);
		log.info("Registered event [symbol={}, type={}, value={}]", signature.getSymbol().toAssetsString(), signature.getType(),
				value);
		long eventCountBySignature = countEventsBySignature(event.getSignature(), LocalDateTime.now().minusHours(24L));
		notificationService.sendEventNotification(savedEvent, eventCountBySignature);
		return savedEvent;
	}

	public long countEventsBySignature(EventSignature signature, LocalDateTime time) {
		return eventRepository.countEvents(signature, time);
	}

	public void deleteAllEvents() {
		eventRepository.deleteAll();
	}

}
