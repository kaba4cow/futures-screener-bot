package com.kaba4cow.futuresscreenerbot.config.properties.screener.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application.screeners.settings.long-liquidation")
@Component
public class LongLiquidationScreenerSettingsProperties implements ScreenerSettingsProperties {

	private Double minLongLiquidationThreshold;

	private Double maxLongLiquidationThreshold;

	@Override
	public Double getMinThreshold() {
		return minLongLiquidationThreshold;
	}

	@Override
	public Double getMaxThreshold() {
		return maxLongLiquidationThreshold;
	}

}
