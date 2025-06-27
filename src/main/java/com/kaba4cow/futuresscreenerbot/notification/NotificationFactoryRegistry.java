package com.kaba4cow.futuresscreenerbot.notification;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.EventType;

@Component
public class NotificationFactoryRegistry {

	private final Map<EventType, NotificationFactory> registry = new ConcurrentHashMap<>();

	void register(NotificationFactory factory) {
		EventType eventType = factory.getEventType();
		if (registry.containsKey(eventType))
			throw new RuntimeException(String.format("NotificationFactory for EventType %s already registered", eventType));
		registry.put(eventType, factory);
	}

	public NotificationFactory getFactory(EventType eventType) {
		if (!registry.containsKey(eventType))
			throw new NoSuchElementException(String.format("NotificationFactory for EventType %s not found", eventType));
		return registry.get(eventType);
	}

}
