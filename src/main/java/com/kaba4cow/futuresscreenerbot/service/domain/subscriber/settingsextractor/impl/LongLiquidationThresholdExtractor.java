package com.kaba4cow.futuresscreenerbot.service.domain.subscriber.settingsextractor.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.service.domain.subscriber.settingsextractor.SubscriberSettingsExtractor;

@Component
public class LongLiquidationThresholdExtractor implements SubscriberSettingsExtractor {

	@Override
	public Double getThreshold(SubscriberSettings settings) {
		return settings.getLongLiquidationThreshold();
	}

	@Override
	public EventType getEventType() {
		return EventType.LONG_LIQUIDATION;
	}

}
