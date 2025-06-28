package com.kaba4cow.futuresscreenerbot.service.domain.subscriber.settingsextractor.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.entity.subscriber.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.service.domain.subscriber.settingsextractor.SubscriberSettingsExtractor;

@Component
public class ShortLiquidationThresholdExtractor implements SubscriberSettingsExtractor {

	@Override
	public Double getThreshold(SubscriberSettings settings) {
		return settings.getShortLiquidationThreshold();
	}

	@Override
	public EventType getEventType() {
		return EventType.SHORT_LIQUIDATION;
	}

}
