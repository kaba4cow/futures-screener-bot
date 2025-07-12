package com.kaba4cow.futuresscreenerbot.config.properties.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application.screeners.settings.long-liquidation")
public class LongLiquidationScreenerSettingsProperties implements ScreenerSettingsProperties {

	private final Double minLongLiquidationThreshold;

	private final Double maxLongLiquidationThreshold;

	@Override
	public Double getMinThreshold() {
		return minLongLiquidationThreshold;
	}

	@Override
	public Double getMaxThreshold() {
		return maxLongLiquidationThreshold;
	}

}
