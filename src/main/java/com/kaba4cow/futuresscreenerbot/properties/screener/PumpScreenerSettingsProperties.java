package com.kaba4cow.futuresscreenerbot.properties.screener;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.screeners.settings.pump")
@Component
public class PumpScreenerSettingsProperties {

	private Double minPumpThreshold;

	private Double maxPumpThreshold;

}
