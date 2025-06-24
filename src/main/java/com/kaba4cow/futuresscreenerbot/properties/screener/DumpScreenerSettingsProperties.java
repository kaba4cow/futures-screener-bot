package com.kaba4cow.futuresscreenerbot.properties.screener;

import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.screeners.settings.dump")
@Component
public class DumpScreenerSettingsProperties {

	private BigDecimal minDumpThreshold;

	private BigDecimal maxDumpThreshold;

}
