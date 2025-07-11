package com.kaba4cow.futuresscreenerbot.external.screener.impl;

import org.json.JSONObject;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.LongLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.ShortLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.external.screener.Screener;
import com.kaba4cow.futuresscreenerbot.service.domain.event.EventService;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LiquidationScreener implements Screener {

	private final Symbol symbol;

	private final ShortLiquidationScreenerSettingsProperties shortLiquidationScreenerSettingsProperties;

	private final LongLiquidationScreenerSettingsProperties longLiquidationScreenerSettingsProperties;

	private final EventService eventService;

	@Override
	public void update(JSONObject jsonData) {
		String side = jsonData.getJSONObject("o").getString("S");
		float price = jsonData.getJSONObject("o").getFloat("p");
		float quantity = jsonData.getJSONObject("o").getFloat("q");
		double liquidation = price * quantity;
		if (side.equals("SELL") && liquidation >= longLiquidationScreenerSettingsProperties.getMinThreshold())
			eventService.registerEvent(EventType.LONG_LIQUIDATION.signatureFor(symbol), liquidation);
		if (side.equals("BUY") && liquidation >= shortLiquidationScreenerSettingsProperties.getMinThreshold())
			eventService.registerEvent(EventType.SHORT_LIQUIDATION.signatureFor(symbol), liquidation);
	}

	@Override
	public String getStreamSuffix() {
		return "forceOrder";
	}

	@Override
	public Symbol getSymbol() {
		return symbol;
	}

}
