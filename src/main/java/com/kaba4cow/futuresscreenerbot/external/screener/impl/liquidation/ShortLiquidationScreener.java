package com.kaba4cow.futuresscreenerbot.external.screener.impl.liquidation;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.ShortLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.event.EventType;

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
