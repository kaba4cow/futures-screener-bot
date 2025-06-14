package com.kaba4cow.futuresscreenerbot.screener.factory.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.properties.screener.LiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.screener.Screener;
import com.kaba4cow.futuresscreenerbot.screener.factory.ScreenerFactory;
import com.kaba4cow.futuresscreenerbot.screener.impl.LiquidationScreener;
import com.kaba4cow.futuresscreenerbot.service.domain.EventService;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LiquidationScreenerFactory implements ScreenerFactory {

	private final LiquidationScreenerSettingsProperties liquidationScreenerSettingsProperties;

	private final EventService eventService;

	@Override
	public Screener createScreener(Symbol symbol) {
		return new LiquidationScreener(symbol, liquidationScreenerSettingsProperties, eventService);
	}

}
