package com.kaba4cow.futuresscreenerbot.infra.screener.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.settings.LongLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.domain.event.EventType;

@Component
public class LongLiquidationScreener extends LiquidationScreener<LongLiquidationScreenerSettingsProperties> {

	@Override
	protected String getTargetSide() {
		return "SELL";
	}

	@Override
	public EventType getEventType() {
		return EventType.LONG_LIQUIDATION;
	}

}
