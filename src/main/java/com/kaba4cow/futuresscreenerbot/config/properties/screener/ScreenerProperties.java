package com.kaba4cow.futuresscreenerbot.config.properties.screener;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "application.screeners")
public class ScreenerProperties {

	private final Set<String> baseAssets;

	private final String quoteAsset;

}
