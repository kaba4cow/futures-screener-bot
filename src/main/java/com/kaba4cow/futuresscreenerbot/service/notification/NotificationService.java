package com.kaba4cow.futuresscreenerbot.service.notification;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.entity.event.Event;
import com.kaba4cow.futuresscreenerbot.entity.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.service.domain.event.EventService;
import com.kaba4cow.futuresscreenerbot.service.domain.subscriber.SubscriberService;
import com.kaba4cow.futuresscreenerbot.service.telegram.TelegramMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

	private final SubscriberService subscriberService;

	private final EventService eventService;

	private final NotificationFactoryRegistry notificationFactoryRegistry;

	private final TelegramMessageService telegramMessageService;

	public void sendEventNotification(Event event) {
		List<Subscriber> subscribers = subscriberService.getSubscribersForEvent(event);
		if (subscribers.isEmpty())
			return;
		TelegramMessage message = createMessage(getChatIds(subscribers), event);
		telegramMessageService.sendMessage(message);
		log.info("Sent event notification [type={}, symbol={}] to {} subscribers", event.getType(),
				event.getSymbol().toAssetsString(), subscribers.size());
	}

	private Set<Long> getChatIds(Collection<Subscriber> subscribers) {
		return subscribers.stream()//
				.map(Subscriber::getId)//
				.collect(Collectors.toSet());
	}

	private TelegramMessage createMessage(Set<Long> chatIds, Event event) {
		long eventCount = eventService.countEvents(event, LocalDateTime.now().minusHours(24L));
		NotificationFactory notificationFactory = notificationFactoryRegistry.getFactory(event.getType());
		return notificationFactory.createMessage(chatIds, event, eventCount);
	}

}
