package com.kaba4cow.futuresscreenerbot.external.screener.impl;

import org.json.JSONObject;

import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.external.screener.Screener;
import com.kaba4cow.futuresscreenerbot.external.screener.ScreenerType;
import com.kaba4cow.futuresscreenerbot.properties.screener.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.properties.screener.PumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.service.domain.event.EventService;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;
import com.kaba4cow.futuresscreenerbot.tool.barseries.Bar;
import com.kaba4cow.futuresscreenerbot.tool.barseries.BarSeries;
import com.kaba4cow.futuresscreenerbot.tool.util.MathUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PumpAndDumpScreener implements Screener {

	private final Symbol symbol;

	private final PumpScreenerSettingsProperties pumpScreenerSettingsProperties;

	private final DumpScreenerSettingsProperties dumpScreenerSettingsProperties;

	private final EventService eventService;

	private final BarSeries barSeries = new BarSeries(2);

	@Override
	public void updateScreener(JSONObject jsonData) {
		Bar newBar = new Bar(jsonData);
		if (barSeries.addBar(newBar) && barSeries.getBarCount() == barSeries.getMaxBarCount()) {
			Bar firstBar = barSeries.getFirst();
			Bar lastBar = barSeries.getLast();
			float firstPrice = firstBar.getClosePrice();
			float lastPrice = lastBar.getClosePrice();
			double deltaPrice = MathUtil.calculateDelta(firstPrice, lastPrice);
			if (deltaPrice > 0f && deltaPrice >= pumpScreenerSettingsProperties.getMinPumpThreshold())
				eventService.registerEvent(EventType.PUMP, symbol, deltaPrice);
			if (deltaPrice < 0f && deltaPrice <= -dumpScreenerSettingsProperties.getMinDumpThreshold())
				eventService.registerEvent(EventType.DUMP, symbol, -deltaPrice);
		}
	}

	@Override
	public ScreenerType getScreenerType() {
		return ScreenerType.PUMP_AND_DUMP;
	}

	@Override
	public String getStreamSuffix() {
		return "@kline_1m";
	}

	@Override
	public Symbol getSymbol() {
		return symbol;
	}

}
