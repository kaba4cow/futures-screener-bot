package com.kaba4cow.futuresscreenerbot.config.properties.screener;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.screeners")
@Component
public class ScreenerProperties {

	private List<String> excludedBaseAssets;

	private String quoteAsset;

}
