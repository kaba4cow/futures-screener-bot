package com.kaba4cow.futuresscreenerbot.service.domain.settingsextractor.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.service.domain.settingsextractor.SubscriberSettingsExtractor;

@Component
public class DumpThresholdExtractor implements SubscriberSettingsExtractor {

	@Override
	public BigDecimal getThreshold(SubscriberSettings settings) {
		return settings.getDumpThreshold();
	}

	@Override
	public EventType getEventType() {
		return EventType.DUMP;
	}

}
