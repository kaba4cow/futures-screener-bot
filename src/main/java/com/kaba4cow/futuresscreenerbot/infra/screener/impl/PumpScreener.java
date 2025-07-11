package com.kaba4cow.futuresscreenerbot.infra.screener.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.settings.PumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.domain.event.EventType;
import com.kaba4cow.futuresscreenerbot.util.MathUtil;

@Component
public class PumpScreener extends DeltaScreener<PumpScreenerSettingsProperties> {

	@Override
	protected double calculateDelta(double firstPrice, double lastPrice) {
		return MathUtil.calculateDelta(firstPrice, lastPrice);
	}

	@Override
	public EventType getEventType() {
		return EventType.PUMP;
	}

}
