package com.kaba4cow.futuresscreenerbot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettingsProvider;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.subscribers.settings")
@Component
public class SubscriberSettingsProperties implements SubscriberSettingsProvider {

	private Double pumpThreshold;

	private Double dumpThreshold;

	private Double longLiquidationThreshold;

	private Double shortLiquidationThreshold;

}
