package com.kaba4cow.futuresscreenerbot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.telegram.bot")
@Component
public class TelegramBotProperties {

	private String name;

	private String token;

}
