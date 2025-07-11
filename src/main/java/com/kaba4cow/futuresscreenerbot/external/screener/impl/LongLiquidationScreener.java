package com.kaba4cow.futuresscreenerbot.external.screener.impl;

import org.json.JSONObject;

import com.kaba4cow.futuresscreenerbot.entity.event.EventType;

public class LongLiquidationScreener extends AbstractScreener {

	@Override
	public void update(JSONObject jsonData) {
		String side = jsonData.getJSONObject("o").getString("S");
		if (side.equals("SELL")) {
			float price = jsonData.getJSONObject("o").getFloat("p");
			float quantity = jsonData.getJSONObject("o").getFloat("q");
			double liquidation = price * quantity;
			if (liquidation >= getSettingsThreshold())
				registerEvent(liquidation);
		}
	}

	@Override
	public String getStreamSuffix() {
		return "forceOrder";
	}

	@Override
	public EventType getEventType() {
		return EventType.LONG_LIQUIDATION;
	}

}
