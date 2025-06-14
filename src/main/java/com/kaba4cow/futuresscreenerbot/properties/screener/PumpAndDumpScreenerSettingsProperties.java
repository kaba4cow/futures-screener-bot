package com.kaba4cow.futuresscreenerbot.properties.screener;

import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.screeners.settings.pump-and-dump")
@Component
public class PumpAndDumpScreenerSettingsProperties {

	private BigDecimal minPumpThreshold;

	private BigDecimal maxPumpThreshold;

	private BigDecimal minDumpThreshold;

	private BigDecimal maxDumpThreshold;

}
