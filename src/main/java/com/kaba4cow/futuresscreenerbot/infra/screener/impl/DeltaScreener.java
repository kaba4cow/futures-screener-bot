package com.kaba4cow.futuresscreenerbot.infra.screener.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;

import com.kaba4cow.futuresscreenerbot.config.properties.settings.ScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.domain.Symbol;
import com.kaba4cow.futuresscreenerbot.domain.barseries.Bar;
import com.kaba4cow.futuresscreenerbot.domain.barseries.BarSeries;
import com.kaba4cow.futuresscreenerbot.infra.stream.impl.KLineScreenerStream;

public abstract class DeltaScreener<T extends ScreenerSettingsProperties> extends AbstractScreener<T, KLineScreenerStream> {

	private final Map<Symbol, BarSeries> map = new ConcurrentHashMap<>();

	@Override
	public final void update(Symbol symbol, JSONObject jsonData) {
		Bar newBar = new Bar(jsonData);
		BarSeries barSeries = getBarSeries(symbol);
		if (barSeries.addBar(newBar) && barSeries.getBarCount() == barSeries.getMaxBarCount()) {
			Bar firstBar = barSeries.getFirst();
			Bar lastBar = barSeries.getLast();
			float firstPrice = firstBar.getClosePrice();
			float lastPrice = lastBar.getClosePrice();
			double deltaPrice = calculateDelta(firstPrice, lastPrice);
			if (deltaPrice > 0.0 && deltaPrice >= getSettingsThreshold())
				registerEvent(symbol, deltaPrice);
		}
	}

	protected abstract double calculateDelta(double firstPrice, double lastPrice);

	protected BarSeries getBarSeries(Symbol symbol) {
		return map.computeIfAbsent(symbol, key -> new BarSeries(2));
	}

}
