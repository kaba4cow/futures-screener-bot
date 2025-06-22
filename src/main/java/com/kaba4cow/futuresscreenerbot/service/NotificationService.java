package com.kaba4cow.futuresscreenerbot.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberState;
import com.kaba4cow.futuresscreenerbot.notification.Notification;
import com.kaba4cow.futuresscreenerbot.notification.writer.NotificationWriter;
import com.kaba4cow.futuresscreenerbot.notification.writer.NotificationWriterRegistry;
import com.kaba4cow.futuresscreenerbot.properties.telegram.TelegramProperties;
import com.kaba4cow.futuresscreenerbot.repository.EventRepository;
import com.kaba4cow.futuresscreenerbot.repository.SubscriberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

	private final TelegramProperties telegramProperties;

	private final SubscriberRepository subscriberRepository;

	private final EventRepository eventRepository;

	private final NotificationWriterRegistry notificationWriterRegistry;

	private final ApplicationEventPublisher applicationEventPublisher;

	public void sendEventNotification(Event event) {
		List<Subscriber> subscribers = subscriberRepository.findAllSubscribersByState(SubscriberState.SUBSCRIBED);
		if (subscribers.isEmpty())
			return;
		ApplicationEvent applicationEvent = createNotification(event).prepareEvent(telegramProperties)
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
