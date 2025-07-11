package com.kaba4cow.futuresscreenerbot.external.screener.support.factory.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.config.properties.screener.PumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.external.screener.Screener;
import com.kaba4cow.futuresscreenerbot.external.screener.impl.PumpAndDumpScreener;
import com.kaba4cow.futuresscreenerbot.external.screener.support.factory.ScreenerFactory;
import com.kaba4cow.futuresscreenerbot.service.domain.event.EventService;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PumpAndDumpScreenerFactory implements ScreenerFactory {

	private final PumpScreenerSettingsProperties pumpScreenerSettingsProperties;

	private final DumpScreenerSettingsProperties dumpScreenerSettingsProperties;

	private final EventService eventService;

	@Override
	public Screener createScreener(Symbol symbol) {
		return new PumpAndDumpScreener(symbol, pumpScreenerSettingsProperties, dumpScreenerSettingsProperties, eventService);
	}

}
