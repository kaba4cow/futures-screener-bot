package com.kaba4cow.futuresscreenerbot.external.screener.impl.delta;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;
import com.kaba4cow.futuresscreenerbot.tool.barseries.Bar;
import com.kaba4cow.futuresscreenerbot.tool.barseries.BarSeries;
import com.kaba4cow.futuresscreenerbot.tool.util.MathUtil;

@Component
public class DumpScreener extends DeltaScreener<DumpScreenerSettingsProperties> {

	private final Map<Symbol, BarSeries> map = new ConcurrentHashMap<>();

	@Override
	public void update(Symbol symbol, JSONObject jsonData) {
		Bar newBar = new Bar(jsonData);
		BarSeries barSeries = getBarSeries(symbol);
		if (barSeries.addBar(newBar) && barSeries.getBarCount() == barSeries.getMaxBarCount()) {
			Bar firstBar = barSeries.getFirst();
			Bar lastBar = barSeries.getLast();
			float firstPrice = firstBar.getClosePrice();
			float lastPrice = lastBar.getClosePrice();
			if (lastPrice < firstPrice) {
				double deltaPrice = -MathUtil.calculateDelta(firstPrice, lastPrice);
				if (deltaPrice >= getSettingsThreshold())
					registerEvent(symbol, deltaPrice);
			}
		}
	}

	private BarSeries getBarSeries(Symbol symbol) {
		return map.computeIfAbsent(symbol, key -> new BarSeries(2));
	}

	@Override
	public EventType getEventType() {
		return EventType.DUMP;
	}

}
