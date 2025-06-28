package com.kaba4cow.futuresscreenerbot.external.screener.factory.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.external.screener.Screener;
import com.kaba4cow.futuresscreenerbot.external.screener.factory.ScreenerFactory;
import com.kaba4cow.futuresscreenerbot.external.screener.impl.LiquidationScreener;
import com.kaba4cow.futuresscreenerbot.properties.screener.LongLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.properties.screener.ShortLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.service.domain.event.EventService;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LiquidationScreenerFactory implements ScreenerFactory {

	private final ShortLiquidationScreenerSettingsProperties shortLiquidationScreenerSettingsProperties;

	private final LongLiquidationScreenerSettingsProperties longLiquidationScreenerSettingsProperties;

	private final EventService eventService;

	@Override
	public Screener createScreener(Symbol symbol) {
		return new LiquidationScreener(symbol, shortLiquidationScreenerSettingsProperties,
				longLiquidationScreenerSettingsProperties, eventService);
	}

}
