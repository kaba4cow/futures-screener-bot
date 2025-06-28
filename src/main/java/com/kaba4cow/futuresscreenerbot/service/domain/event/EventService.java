package com.kaba4cow.futuresscreenerbot.service.domain.event;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.entity.event.Event;
import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.repository.EventRepository;
import com.kaba4cow.futuresscreenerbot.service.notification.NotificationService;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventService {

	private final EventRepository eventRepository;

	private final NotificationService notificationService;

	public Event registerEvent(EventType type, Symbol symbol, Double value) {
		Event event = new Event();
		event.setSymbol(symbol);
		event.setType(type);
		event.setValue(value);
		Event savedEvent = eventRepository.save(event);
		log.info("Registered event [symbol={}, type={}, value={}]", symbol.toAssetsString(), type, value);
		notificationService.sendEventNotification(savedEvent);
		return savedEvent;
	}

	public long countEvents(Event event, LocalDateTime time) {
		return eventRepository.countEvents(event.getType(), event.getSymbol(), time);
	}

	public void deleteAllEvents() {
		eventRepository.deleteAll();
	}

}
