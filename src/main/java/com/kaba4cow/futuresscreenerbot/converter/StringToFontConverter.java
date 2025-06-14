package com.kaba4cow.futuresscreenerbot.converter;

import java.awt.Font;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@ConfigurationPropertiesBinding
@Component
public class StringToFontConverter implements Converter<String, Font> {

	@Override
	public Font convert(String source) {
		String[] parts = source.split(" ");
		String fontName = parts[0].trim();
		int fontSize = Integer.parseInt(parts[1].trim());
		return new Font(fontName, Font.PLAIN, fontSize);
	}

}
