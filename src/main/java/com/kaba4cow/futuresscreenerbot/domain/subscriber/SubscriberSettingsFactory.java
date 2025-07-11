package com.kaba4cow.futuresscreenerbot.domain.subscriber;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.SubscriberSettingsProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SubscriberSettingsFactory {

	private final SubscriberSettingsProperties subscriberSettingsProperties;

	public SubscriberSettings createSettings() {
		SubscriberSettings settings = new SubscriberSettings();
		settings.setPumpThreshold(subscriberSettingsProperties.getPumpThreshold());
		settings.setDumpThreshold(subscriberSettingsProperties.getDumpThreshold());
		settings.setLongLiquidationThreshold(subscriberSettingsProperties.getLongLiquidationThreshold());
		settings.setShortLiquidationThreshold(subscriberSettingsProperties.getShortLiquidationThreshold());
		return settings;
	}

}
