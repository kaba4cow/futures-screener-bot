package com.kaba4cow.futuresscreenerbot.screener.factory.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.properties.screener.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.properties.screener.PumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.screener.Screener;
import com.kaba4cow.futuresscreenerbot.screener.factory.ScreenerFactory;
import com.kaba4cow.futuresscreenerbot.screener.impl.PumpAndDumpScreener;
import com.kaba4cow.futuresscreenerbot.service.domain.EventService;
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
