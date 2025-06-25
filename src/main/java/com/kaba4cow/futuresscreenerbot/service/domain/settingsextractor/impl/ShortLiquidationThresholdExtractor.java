package com.kaba4cow.futuresscreenerbot.service.domain.settingsextractor.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.service.domain.settingsextractor.SubscriberSettingsExtractor;

@Component
public class ShortLiquidationThresholdExtractor implements SubscriberSettingsExtractor {

	@Override
	public BigDecimal getThreshold(SubscriberSettings settings) {
		return settings.getShortLiquidationThreshold();
	}

	@Override
	public EventType getEventType() {
		return EventType.SHORT_LIQUIDATION;
	}

}
