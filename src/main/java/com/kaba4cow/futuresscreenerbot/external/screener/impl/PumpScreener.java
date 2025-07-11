package com.kaba4cow.futuresscreenerbot.external.screener.impl;

import org.json.JSONObject;

import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.tool.barseries.Bar;
import com.kaba4cow.futuresscreenerbot.tool.barseries.BarSeries;
import com.kaba4cow.futuresscreenerbot.tool.util.MathUtil;

public class PumpScreener extends AbstractScreener {

	private final BarSeries barSeries = new BarSeries(2);

	@Override
	public void update(JSONObject jsonData) {
		Bar newBar = new Bar(jsonData);
		if (barSeries.addBar(newBar) && barSeries.getBarCount() == barSeries.getMaxBarCount()) {
			Bar firstBar = barSeries.getFirst();
			Bar lastBar = barSeries.getLast();
			float firstPrice = firstBar.getClosePrice();
			float lastPrice = lastBar.getClosePrice();
			if (lastPrice > firstPrice) {
				double deltaPrice = MathUtil.calculateDelta(firstPrice, lastPrice);
				if (deltaPrice >= getSettingsThreshold())
					registerEvent(deltaPrice);
			}
		}
	}

	@Override
	public String getStreamSuffix() {
		return "kline_1m";
	}

	@Override
	public EventType getEventType() {
		return EventType.PUMP;
	}

}
