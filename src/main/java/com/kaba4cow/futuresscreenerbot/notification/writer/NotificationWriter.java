package com.kaba4cow.futuresscreenerbot.notification.writer;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.notification.Notification;

public interface NotificationWriter {

	Notification createNotification(Event event, long eventCount);

	EventType getEventType();

	@Autowired
	default void registerSelf(NotificationWriterRegistry registry) {
		registry.registerNotificationWriter(this);
	}

}
