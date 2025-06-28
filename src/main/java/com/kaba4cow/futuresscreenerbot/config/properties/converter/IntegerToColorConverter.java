package com.kaba4cow.futuresscreenerbot.config.properties.converter;

import java.awt.Color;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@ConfigurationPropertiesBinding
@Component
public class IntegerToColorConverter implements Converter<Integer, Color> {

	@Override
	public Color convert(Integer source) {
		return new Color(source);
	}

}
