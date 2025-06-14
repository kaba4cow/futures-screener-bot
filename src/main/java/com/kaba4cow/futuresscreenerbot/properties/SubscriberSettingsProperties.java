package com.kaba4cow.futuresscreenerbot.properties;

import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.subscribers.settings")
@Component
public class SubscriberSettingsProperties {

	private BigDecimal pumpThreshold;
	
	private BigDecimal dumpThreshold;
	
	private BigDecimal longLiquidationThreshold;
	
	private BigDecimal shortLiquidationThreshold;

}
