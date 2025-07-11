package com.kaba4cow.futuresscreenerbot.config.properties.screener.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application.screeners.settings.pump")
@Component
public class PumpScreenerSettingsProperties implements ScreenerSettingsProperties {

	private Double minPumpThreshold;

	private Double maxPumpThreshold;

	@Override
	public Double getMinThreshold() {
		return minPumpThreshold;
	}

	@Override
	public Double getMaxThreshold() {
		return maxPumpThreshold;
	}

}
