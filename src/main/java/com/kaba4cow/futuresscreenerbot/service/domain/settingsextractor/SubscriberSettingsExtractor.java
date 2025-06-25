package com.kaba4cow.futuresscreenerbot.service.domain.settingsextractor;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;

public interface SubscriberSettingsExtractor {

	@Autowired
	default void registerSelf(SubscriberSettingsExtractorRegistry registry) {
		registry.register(this);
	}

	BigDecimal getThreshold(SubscriberSettings settings);

	EventType getEventType();

}
