package com.kaba4cow.futuresscreenerbot.service.domain.event;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.entity.event.Event;
import com.kaba4cow.futuresscreenerbot.entity.event.EventSignature;
import com.kaba4cow.futuresscreenerbot.repository.EventRepository;
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
		long eventCountBySignature = countEvents(event, LocalDateTime.now().minusHours(24L));
		notificationService.sendEventNotification(savedEvent, eventCountBySignature);
		return savedEvent;
	}

	public long countEvents(Event event, LocalDateTime time) {
		return eventRepository.countEvents(event.getSignature(), time);
	}

	public void deleteAllEvents() {
		eventRepository.deleteAll();
	}

}
