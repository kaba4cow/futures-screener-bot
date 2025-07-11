package com.kaba4cow.futuresscreenerbot.external.screener.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.ScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.event.EventSignature;
import com.kaba4cow.futuresscreenerbot.external.screener.Screener;
import com.kaba4cow.futuresscreenerbot.external.screener.stream.ScreenerStream;
import com.kaba4cow.futuresscreenerbot.service.domain.event.EventService;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

public abstract class AbstractScreener<T extends ScreenerSettingsProperties, S extends ScreenerStream> implements Screener {

	private EventService eventService;

	private T settings;

	private S stream;

	protected void registerEvent(Symbol symbol, Double value) {
		EventSignature signature = getEventType().signatureFor(symbol);
		eventService.registerEvent(signature, value);
	}

	protected Double getSettingsThreshold() {
		return settings.getMinThreshold();
	}

	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@Autowired
	public void setSettings(T settings) {
		this.settings = settings;
	}

	@Autowired
	public void setStream(S stream) {
		this.stream = stream;
	}

	@Override
	public ScreenerStream getStream() {
		return stream;
	}

}
