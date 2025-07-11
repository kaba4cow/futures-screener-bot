package com.kaba4cow.futuresscreenerbot.external.screener.support.factory.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.external.screener.Screener;
import com.kaba4cow.futuresscreenerbot.external.screener.impl.AbstractScreener;
import com.kaba4cow.futuresscreenerbot.external.screener.impl.DumpScreener;
import com.kaba4cow.futuresscreenerbot.external.screener.support.factory.ScreenerFactory;
import com.kaba4cow.futuresscreenerbot.service.domain.event.EventService;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DumpScreenerFactory implements ScreenerFactory {

	private final DumpScreenerSettingsProperties dumpScreenerSettingsProperties;

	private final EventService eventService;

	@Override
	public Screener createScreener(Symbol symbol) {
		AbstractScreener screener = new DumpScreener();
		screener.setSymbol(symbol);
		screener.setEventService(eventService);
		screener.setSettings(dumpScreenerSettingsProperties);
		return screener;
	}

}
