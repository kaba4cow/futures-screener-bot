package com.kaba4cow.futuresscreenerbot.properties.screener;

import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.screeners.settings.liquidation")
@Component
public class LiquidationScreenerSettingsProperties {

	private BigDecimal minLongLiquidationThreshold;

	private BigDecimal maxLongLiquidationThreshold;

	private BigDecimal minShortLiquidationThreshold;

	private BigDecimal maxShortLiquidationThreshold;

}
