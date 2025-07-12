package com.kaba4cow.futuresscreenerbot.config.properties.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application.screeners.settings.pump")
public class PumpScreenerSettingsProperties implements ScreenerSettingsProperties {

	private final Double minPumpThreshold;

	private final Double maxPumpThreshold;

	@Override
	public Double getMinThreshold() {
		return minPumpThreshold;
	}

	@Override
	public Double getMaxThreshold() {
		return maxPumpThreshold;
	}

}
