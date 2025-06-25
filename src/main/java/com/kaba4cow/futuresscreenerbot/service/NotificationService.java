package com.kaba4cow.futuresscreenerbot.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.event.message.TelegramMessageEvent;
import com.kaba4cow.futuresscreenerbot.notification.Notification;
import com.kaba4cow.futuresscreenerbot.notification.writer.NotificationWriter;
import com.kaba4cow.futuresscreenerbot.notification.writer.NotificationWriterRegistry;
import com.kaba4cow.futuresscreenerbot.properties.TemplateProperties;
import com.kaba4cow.futuresscreenerbot.repository.EventRepository;
import com.kaba4cow.futuresscreenerbot.service.domain.SubscriberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

	private final TemplateProperties templateProperties;

	private final SubscriberService subscriberService;

	private final EventRepository eventRepository;

	private final NotificationWriterRegistry notificationWriterRegistry;

	private final ApplicationEventPublisher applicationEventPublisher;

	public void sendEventNotification(Event event) {
		List<Subscriber> subscribers = subscriberService.getSubscribersForEvent(event);
		if (subscribers.isEmpty())
			return;
		TelegramMessageEvent applicationEvent = createNotification(event)//
				.prepareEvent(templateProperties)//
				.apply(getChatIds(subscribers));
		applicationEventPublisher.publishEvent(applicationEvent);
		log.info("Sent event notification [type={}, symbol={}] to {} subscribers", event.getType(),
				event.getSymbol().toAssetsString(), subscribers.size());
	}

	private Set<Long> getChatIds(Collection<Subscriber> subscribers) {
		return subscribers.stream()//
				.map(Subscriber::getId)//
				.collect(Collectors.toSet());
	}

	private Notification createNotification(Event event) {
		long eventCount = eventRepository.countEvents(event, LocalDateTime.now().minusHours(24L));
		NotificationWriter notificationWriter = notificationWriterRegistry.getNotificationWriter(event.getType());
		return notificationWriter.createNotification(event, eventCount);
	}

}
