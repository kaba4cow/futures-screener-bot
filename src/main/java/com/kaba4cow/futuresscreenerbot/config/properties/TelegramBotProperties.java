package com.kaba4cow.futuresscreenerbot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "application.telegram.bot")
public class TelegramBotProperties {

	private final String name;

	private final String token;

}
