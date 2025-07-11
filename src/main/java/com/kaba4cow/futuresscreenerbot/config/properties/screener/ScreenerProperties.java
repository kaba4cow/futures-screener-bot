package com.kaba4cow.futuresscreenerbot.config.properties.screener;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.screeners")
@Component
public class ScreenerProperties {

	private Set<String> baseAssets;

	private String quoteAsset;

}
