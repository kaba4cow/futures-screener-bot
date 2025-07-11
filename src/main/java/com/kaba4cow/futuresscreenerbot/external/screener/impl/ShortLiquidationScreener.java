package com.kaba4cow.futuresscreenerbot.external.screener.impl;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.ShortLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.external.screener.stream.impl.ForceOrderScreenerStream;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

@Component
public class ShortLiquidationScreener
		extends AbstractScreener<ShortLiquidationScreenerSettingsProperties, ForceOrderScreenerStream> {

	@Override
	public void update(Symbol symbol, JSONObject jsonData) {
		String side = jsonData.getJSONObject("o").getString("S");
		if (side.equals("BUY")) {
			float price = jsonData.getJSONObject("o").getFloat("p");
			float quantity = jsonData.getJSONObject("o").getFloat("q");
			double liquidation = price * quantity;
			if (liquidation >= getSettingsThreshold())
				registerEvent(symbol, liquidation);
		}
	}

	@Override
	public EventType getEventType() {
		return EventType.SHORT_LIQUIDATION;
	}

}
