package com.kaba4cow.futuresscreenerbot.screener.impl;

import java.math.BigDecimal;

import org.json.JSONObject;

import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.properties.screener.LongLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.properties.screener.ShortLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.screener.Screener;
import com.kaba4cow.futuresscreenerbot.screener.ScreenerType;
import com.kaba4cow.futuresscreenerbot.service.domain.EventService;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LiquidationScreener implements Screener {

	private final Symbol symbol;

	private final ShortLiquidationScreenerSettingsProperties shortLiquidationScreenerSettingsProperties;

	private final LongLiquidationScreenerSettingsProperties longLiquidationScreenerSettingsProperties;

	private final EventService eventService;

	@Override
	public void updateScreener(JSONObject jsonData) {
		String side = jsonData.getJSONObject("o").getString("S");
		BigDecimal price = jsonData.getJSONObject("o").getBigDecimal("p");
		BigDecimal quantity = jsonData.getJSONObject("o").getBigDecimal("q");
		BigDecimal liquidation = price.multiply(quantity);
		if (side.equals("SELL") && liquidation.doubleValue() >= longLiquidationScreenerSettingsProperties
				.getMinLongLiquidationThreshold().doubleValue())
			eventService.registerEvent(EventType.LONG_LIQUIDATION, symbol, liquidation);
		if (side.equals("BUY") && liquidation.doubleValue() >= shortLiquidationScreenerSettingsProperties
				.getMinShortLiquidationThreshold().doubleValue())
			eventService.registerEvent(EventType.SHORT_LIQUIDATION, symbol, liquidation);
	}

	@Override
	public ScreenerType getScreenerType() {
		return ScreenerType.LIQUIDATION;
	}

	@Override
	public String getStreamSuffix() {
		return "@forceOrder";
	}

	@Override
	public Symbol getSymbol() {
		return symbol;
	}

}
