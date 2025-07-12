package com.kaba4cow.futuresscreenerbot.domain.subscriber.extractor;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.domain.event.EventType;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberSettings;

public interface SubscriberSettingsExtractor {

	@Autowired
	default void registerSelf(SubscriberSettingsExtractorRegistry registry) {
		registry.register(this);
	}

	Double getThreshold(SubscriberSettings settings);

	EventType getEventType();

}
