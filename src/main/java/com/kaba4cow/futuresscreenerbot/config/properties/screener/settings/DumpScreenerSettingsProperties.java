package com.kaba4cow.futuresscreenerbot.config.properties.screener.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application.screeners.settings.dump")
public class DumpScreenerSettingsProperties implements ScreenerSettingsProperties {

	private final Double minDumpThreshold;

	private final Double maxDumpThreshold;

	@Override
	public Double getMinThreshold() {
		return minDumpThreshold;
	}

	@Override
	public Double getMaxThreshold() {
		return maxDumpThreshold;
	}

}
