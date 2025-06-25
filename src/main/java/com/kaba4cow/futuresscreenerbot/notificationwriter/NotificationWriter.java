package com.kaba4cow.futuresscreenerbot.notificationwriter;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;

public interface NotificationWriter {

	TelegramMessage createMessage(Set<Long> chatIds, Event event, long eventCount);

	EventType getEventType();

	@Autowired
	default void registerSelf(NotificationWriterRegistry registry) {
		registry.registerNotificationWriter(this);
	}

}
