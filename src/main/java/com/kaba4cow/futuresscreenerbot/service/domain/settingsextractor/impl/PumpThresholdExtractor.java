package com.kaba4cow.futuresscreenerbot.service.domain.settingsextractor.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.service.domain.settingsextractor.SubscriberSettingsExtractor;

@Component
public class PumpThresholdExtractor implements SubscriberSettingsExtractor {

	@Override
	public Double getThreshold(SubscriberSettings settings) {
		return settings.getPumpThreshold();
	}

	@Override
	public EventType getEventType() {
		return EventType.PUMP;
	}

}
