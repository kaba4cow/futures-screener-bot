package com.kaba4cow.futuresscreenerbot.properties.telegram;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.telegram")
@Component
public class TelegramProperties {

	private String parseMode;

}
