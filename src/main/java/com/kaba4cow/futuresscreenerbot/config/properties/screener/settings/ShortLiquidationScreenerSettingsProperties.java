package com.kaba4cow.futuresscreenerbot.config.properties.screener.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application.screeners.settings.short-liquidation")
public class ShortLiquidationScreenerSettingsProperties implements ScreenerSettingsProperties {

	private final Double minShortLiquidationThreshold;

	private final Double maxShortLiquidationThreshold;

	@Override
	public Double getMinThreshold() {
		return minShortLiquidationThreshold;
	}

	@Override
	public Double getMaxThreshold() {
		return maxShortLiquidationThreshold;
	}

}
