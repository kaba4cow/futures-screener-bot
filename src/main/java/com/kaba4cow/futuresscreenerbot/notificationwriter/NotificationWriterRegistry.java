package com.kaba4cow.futuresscreenerbot.notificationwriter;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.EventType;

@Component
public class NotificationWriterRegistry {

	private final Map<EventType, NotificationWriter> registry = new ConcurrentHashMap<>();

	public void registerNotificationWriter(NotificationWriter notificationWriter) {
		EventType eventType = notificationWriter.getEventType();
		if (registry.containsKey(eventType))
			throw new RuntimeException(String.format("NotificationWriter for EventType %s already registered", eventType));
		registry.put(eventType, notificationWriter);
	}

	public NotificationWriter getNotificationWriter(EventType eventType) {
		if (!registry.containsKey(eventType))
			throw new NoSuchElementException(String.format("NotificationWriter for EventType %s not found", eventType));
		return registry.get(eventType);
	}

}
