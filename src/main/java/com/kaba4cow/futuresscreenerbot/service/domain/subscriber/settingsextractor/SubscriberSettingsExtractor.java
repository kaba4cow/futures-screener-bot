package com.kaba4cow.futuresscreenerbot.service.domain.subscriber.settingsextractor;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.entity.subscriber.SubscriberSettings;

public interface SubscriberSettingsExtractor {

	@Autowired
	default void registerSelf(SubscriberSettingsExtractorRegistry registry) {
		registry.register(this);
	}

	Double getThreshold(SubscriberSettings settings);

	EventType getEventType();

}
