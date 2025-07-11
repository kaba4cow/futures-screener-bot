package com.kaba4cow.futuresscreenerbot.config.properties.chart;

import java.awt.Font;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "application.chart")
public class ChartProperties {

	private final int barCount;

	private final int barWidth;

	private final int offset;

	private final String interval;

	private final float[] lineStroke;

	private final Font font;

	private final String dateTimePattern;

}
