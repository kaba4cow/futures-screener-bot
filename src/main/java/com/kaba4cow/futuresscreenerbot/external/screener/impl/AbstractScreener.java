package com.kaba4cow.futuresscreenerbot.external.screener.impl;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.ScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.event.EventSignature;
import com.kaba4cow.futuresscreenerbot.external.screener.Screener;
import com.kaba4cow.futuresscreenerbot.service.domain.event.EventService;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import lombok.Getter;
import lombok.Setter;

@Setter
public abstract class AbstractScreener implements Screener {

	private @Getter Symbol symbol;

	private EventService eventService;

	private ScreenerSettingsProperties settings;

	protected void registerEvent(Double value) {
		EventSignature signature = getEventType().signatureFor(symbol);
		eventService.registerEvent(signature, value);
	}

	protected Double getSettingsThreshold() {
		return settings.getMinThreshold();
	}

}
