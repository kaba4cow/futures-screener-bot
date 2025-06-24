package com.kaba4cow.futuresscreenerbot.screener.impl;

import java.math.BigDecimal;

import org.json.JSONObject;

import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.properties.screener.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.properties.screener.PumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.screener.Screener;
import com.kaba4cow.futuresscreenerbot.screener.ScreenerType;
import com.kaba4cow.futuresscreenerbot.service.domain.EventService;
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
			double firstPrice = firstBar.getClosePrice();
			double lastPrice = lastBar.getClosePrice();
			double deltaPrice = MathUtil.calculateDelta(firstPrice, lastPrice);
			if (deltaPrice > 0f && deltaPrice >= pumpScreenerSettingsProperties.getMinPumpThreshold().doubleValue())
				eventService.registerEvent(EventType.PUMP, symbol, BigDecimal.valueOf(deltaPrice));
			if (deltaPrice < 0f && deltaPrice <= -dumpScreenerSettingsProperties.getMinDumpThreshold().doubleValue())
				eventService.registerEvent(EventType.DUMP, symbol, BigDecimal.valueOf(deltaPrice).abs());
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
