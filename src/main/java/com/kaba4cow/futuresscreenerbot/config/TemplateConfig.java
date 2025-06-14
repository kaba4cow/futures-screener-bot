package com.kaba4cow.futuresscreenerbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.loader.ClasspathLoader;

@Configuration
public class TemplateConfig {

	@Bean
	public PebbleEngine templateEngine() {
		return new PebbleEngine.Builder()//
				.loader(new ClasspathLoader())//
				.build();
	}

}
