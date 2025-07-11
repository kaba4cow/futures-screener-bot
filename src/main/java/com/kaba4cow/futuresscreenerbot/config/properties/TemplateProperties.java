package com.kaba4cow.futuresscreenerbot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "application.templates")
public class TemplateProperties {

	private final String prefix;

	private final String suffix;

	private final String parseMode;

}
