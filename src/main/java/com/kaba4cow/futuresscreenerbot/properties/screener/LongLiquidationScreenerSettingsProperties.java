package com.kaba4cow.futuresscreenerbot.properties.screener;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.screeners.settings.long-liquidation")
@Component
public class LongLiquidationScreenerSettingsProperties {

	private Double minLongLiquidationThreshold;

	private Double maxLongLiquidationThreshold;

}
