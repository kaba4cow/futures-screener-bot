package com.kaba4cow.futuresscreenerbot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.kaba4cow.futuresscreenerbot.entity.subscriber.SubscriberSettingsProvider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "application.subscribers.settings")
public class SubscriberSettingsProperties implements SubscriberSettingsProvider {

	private final Double pumpThreshold;

	private final Double dumpThreshold;

	private final Double longLiquidationThreshold;

	private final Double shortLiquidationThreshold;

}
