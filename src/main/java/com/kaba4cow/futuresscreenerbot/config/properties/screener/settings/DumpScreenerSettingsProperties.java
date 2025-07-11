package com.kaba4cow.futuresscreenerbot.config.properties.screener.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application.screeners.settings.dump")
@Component
public class DumpScreenerSettingsProperties implements ScreenerSettingsProperties {

	private Double minDumpThreshold;

	private Double maxDumpThreshold;

	@Override
	public Double getMinThreshold() {
		return minDumpThreshold;
	}

	@Override
	public Double getMaxThreshold() {
		return maxDumpThreshold;
	}

}
