package com.kaba4cow.futuresscreenerbot.service.notification;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.entity.event.Event;
import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramMessage;

public interface NotificationFactory {

	TelegramMessage createMessage(Set<Long> chatIds, Event event, long eventCount);

	EventType getEventType();

	@Autowired
	default void registerSelf(NotificationFactoryRegistry registry) {
		registry.register(this);
	}

}
