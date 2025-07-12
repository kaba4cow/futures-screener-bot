package com.kaba4cow.futuresscreenerbot.service.notification;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.domain.event.Event;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberService;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.service.telegram.TelegramMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

	private final SubscriberService subscriberService;

	private final NotificationFactoryRegistry notificationFactoryRegistry;

	private final TelegramMessageService telegramMessageService;

	public void sendEventNotification(Event event, long eventCountBySignature) {
		List<Subscriber> subscribers = subscriberService.getSubscribersForEvent(event);
		if (subscribers.isEmpty())
			return;
		TelegramMessage message = createMessage(getChatIds(subscribers), event, eventCountBySignature);
		telegramMessageService.sendMessage(message);
		log.info("Sent event notification [type={}, symbol={}] to {} subscribers", event.getSignature().getType(),
				event.getSignature().getSymbol().toAssetsString(), subscribers.size());
	}

	private Set<Long> getChatIds(Collection<Subscriber> subscribers) {
		return subscribers.stream()//
				.map(Subscriber::getId)//
				.collect(Collectors.toSet());
	}

	private TelegramMessage createMessage(Set<Long> chatIds, Event event, long eventCountBySignature) {
		NotificationFactory notificationFactory = notificationFactoryRegistry.getFactory(event.getSignature().getType());
		return notificationFactory.createMessage(chatIds, event, eventCountBySignature);
	}

}
