package com.kaba4cow.futuresscreenerbot.config.properties.chart;

import java.awt.Color;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "application.chart.color")
public class ChartColorProperties {

	private final Color background;

	private final Color text;

	private final Color line;

	private final Color bull;

	private final Color bear;

}
