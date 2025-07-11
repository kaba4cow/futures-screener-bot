package com.kaba4cow.futuresscreenerbot.config.properties.screener.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application.screeners.settings.short-liquidation")
@Component
public class ShortLiquidationScreenerSettingsProperties implements ScreenerSettingsProperties {

	private Double minShortLiquidationThreshold;

	private Double maxShortLiquidationThreshold;

	@Override
	public Double getMinThreshold() {
		return minShortLiquidationThreshold;
	}

	@Override
	public Double getMaxThreshold() {
		return maxShortLiquidationThreshold;
	}

}
