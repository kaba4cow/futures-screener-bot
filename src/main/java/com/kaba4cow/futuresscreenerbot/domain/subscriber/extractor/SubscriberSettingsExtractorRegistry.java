package com.kaba4cow.futuresscreenerbot.domain.subscriber.extractor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.domain.event.EventType;

@Component
public class SubscriberSettingsExtractorRegistry {

	private final Map<EventType, SubscriberSettingsExtractor> extractors = new ConcurrentHashMap<>();

	void register(SubscriberSettingsExtractor extractor) {
		extractors.put(extractor.getEventType(), extractor);
	}

	public SubscriberSettingsExtractor getExtractor(EventType eventType) {
		return extractors.get(eventType);
	}

}
