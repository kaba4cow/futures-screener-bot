package com.kaba4cow.futuresscreenerbot.infra.screener.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.ScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.domain.event.EventService;
import com.kaba4cow.futuresscreenerbot.domain.event.EventSignature;
import com.kaba4cow.futuresscreenerbot.infra.screener.Screener;
import com.kaba4cow.futuresscreenerbot.infra.screener.stream.ScreenerStream;
import com.kaba4cow.futuresscreenerbot.util.tool.Symbol;

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
