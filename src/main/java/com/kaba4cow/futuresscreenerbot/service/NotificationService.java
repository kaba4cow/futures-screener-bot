package com.kaba4cow.futuresscreenerbot.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberState;
import com.kaba4cow.futuresscreenerbot.notification.Notification;
import com.kaba4cow.futuresscreenerbot.notification.writer.NotificationWriter;
import com.kaba4cow.futuresscreenerbot.notification.writer.NotificationWriterRegistry;
import com.kaba4cow.futuresscreenerbot.properties.telegram.TelegramProperties;
import com.kaba4cow.futuresscreenerbot.repository.EventRepository;
import com.kaba4cow.futuresscreenerbot.repository.SubscriberRepository;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

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
		EventType type = event.getType();
		Symbol symbol = event.getSymbol();
		List<Subscriber> subscribers = subscriberRepository.findAllSubscribersByState(SubscriberState.SUBSCRIBED);
		if (subscribers.isEmpty()) {
			log.info("Could not find subscribers to send event notification [type={}, symbol={}/{}]", type,
					symbol.getBaseAsset(), symbol.getQuoteAsset());
			return;
		}
		long eventCount = eventRepository.countEvents(event, LocalDateTime.now().minusHours(24L));
		NotificationWriter notificationWriter = notificationWriterRegistry.getNotificationWriter(type);
		Notification notification = notificationWriter.createNotification(event, eventCount);
		Set<Long> chatIds = subscribers.stream().map(Subscriber::getId).collect(Collectors.toSet());
		ApplicationEvent applicationEvent = notification.prepareEvent(telegramProperties).apply(chatIds);
		applicationEventPublisher.publishEvent(applicationEvent);
		log.info("Sent event notification [type={}, symbol={}/{}] to {} subscribers", type, symbol.getBaseAsset(),
				symbol.getQuoteAsset(), subscribers.size());
	}

}
