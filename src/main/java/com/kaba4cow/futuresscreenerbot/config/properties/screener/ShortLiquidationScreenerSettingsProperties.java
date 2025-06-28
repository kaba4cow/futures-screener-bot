package com.kaba4cow.futuresscreenerbot.config.properties.screener;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.screeners.settings.short-liquidation")
@Component
public class ShortLiquidationScreenerSettingsProperties {

	private Double minShortLiquidationThreshold;

	private Double maxShortLiquidationThreshold;

}
