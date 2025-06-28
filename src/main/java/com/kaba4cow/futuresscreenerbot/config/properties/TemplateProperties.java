package com.kaba4cow.futuresscreenerbot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.templates")
@Component
public class TemplateProperties {

	private String prefix;

	private String suffix;

	private String parseMode;

}
