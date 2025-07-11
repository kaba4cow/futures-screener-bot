package com.kaba4cow.futuresscreenerbot.external.screener.impl;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.PumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.external.screener.stream.impl.KLineScreenerStream;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;
import com.kaba4cow.futuresscreenerbot.tool.barseries.Bar;
import com.kaba4cow.futuresscreenerbot.tool.barseries.BarSeries;
import com.kaba4cow.futuresscreenerbot.tool.util.MathUtil;

@Component
public class PumpScreener extends AbstractScreener<PumpScreenerSettingsProperties, KLineScreenerStream> {

	private final BarSeries barSeries = new BarSeries(2);

	@Override
	public void update(Symbol symbol, JSONObject jsonData) {
		Bar newBar = new Bar(jsonData);
		if (barSeries.addBar(newBar) && barSeries.getBarCount() == barSeries.getMaxBarCount()) {
			Bar firstBar = barSeries.getFirst();
			Bar lastBar = barSeries.getLast();
			float firstPrice = firstBar.getClosePrice();
			float lastPrice = lastBar.getClosePrice();
			if (lastPrice > firstPrice) {
				double deltaPrice = MathUtil.calculateDelta(firstPrice, lastPrice);
				if (deltaPrice >= getSettingsThreshold())
					registerEvent(symbol, deltaPrice);
			}
		}
	}

	@Override
	public EventType getEventType() {
		return EventType.PUMP;
	}

}
