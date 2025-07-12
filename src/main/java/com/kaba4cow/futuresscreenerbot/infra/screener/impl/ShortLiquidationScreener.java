package com.kaba4cow.futuresscreenerbot.infra.screener.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.ShortLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.domain.event.EventType;

@Component
public class ShortLiquidationScreener extends LiquidationScreener<ShortLiquidationScreenerSettingsProperties> {

	@Override
	protected String getTargetSide() {
		return "BUY";
	}

	@Override
	public EventType getEventType() {
		return EventType.SHORT_LIQUIDATION;
	}

}
