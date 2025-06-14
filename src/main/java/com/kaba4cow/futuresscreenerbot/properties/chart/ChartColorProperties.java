package com.kaba4cow.futuresscreenerbot.properties.chart;

import java.awt.Color;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.chart.color")
@Component
public class ChartColorProperties {

	private Color background;

	private Color text;

	private Color line;

	private Color bull;

	private Color bear;

}
