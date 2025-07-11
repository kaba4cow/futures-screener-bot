package com.kaba4cow.futuresscreenerbot.external.screener.impl.delta;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.tool.util.MathUtil;

@Component
public class DumpScreener extends DeltaScreener<DumpScreenerSettingsProperties> {

	@Override
	protected double calculateDelta(double firstPrice, double lastPrice) {
		return -MathUtil.calculateDelta(firstPrice, lastPrice);
	}

	@Override
	public EventType getEventType() {
		return EventType.DUMP;
	}

}
