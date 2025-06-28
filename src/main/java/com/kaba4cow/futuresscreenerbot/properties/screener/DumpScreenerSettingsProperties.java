package com.kaba4cow.futuresscreenerbot.properties.screener;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.screeners.settings.dump")
@Component
public class DumpScreenerSettingsProperties {

	private Double minDumpThreshold;

	private Double maxDumpThreshold;

}
