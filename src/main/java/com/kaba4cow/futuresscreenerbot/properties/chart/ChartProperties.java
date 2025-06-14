package com.kaba4cow.futuresscreenerbot.properties.chart;

import java.awt.Font;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.chart")
@Component
public class ChartProperties {

	private int barCount;

	private int barWidth;

	private int offset;

	private String interval;

	private float[] lineStroke;

	private Font font;

	private String dateTimePattern;

}
