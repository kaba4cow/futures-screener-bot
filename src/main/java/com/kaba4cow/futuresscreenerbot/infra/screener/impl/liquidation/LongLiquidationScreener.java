package com.kaba4cow.futuresscreenerbot.infra.screener.impl.liquidation;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.LongLiquidationScreenerSettingsProperties;
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
