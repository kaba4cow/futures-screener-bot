package com.kaba4cow.futuresscreenerbot.service;

import java.io.StringWriter;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.properties.TemplateProperties;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Service
public class TemplateService {

	private final TemplateProperties templateProperties;

	private final PebbleEngine pebbleEngine;

	@SneakyThrows
	public String evaluateTemplate(String templateName, Map<String, Object> context) {
		String templatePath = buildTemplatePath(templateName);
		PebbleTemplate template = pebbleEngine.getTemplate(templatePath);
		StringWriter writer = new StringWriter();
		template.evaluate(writer, context);
		return writer.toString();
	}

	public String evaluateTemplate(String templateName) {
		return evaluateTemplate(templateName, Map.of());
	}

	private String buildTemplatePath(String templateName) {
		StringBuilder templatePath = new StringBuilder();
		if (Objects.nonNull(templateProperties.getPrefix()))
			templatePath.append(templateProperties.getPrefix());
		templatePath.append(templateName);
		if (Objects.nonNull(templateProperties.getSuffix()))
			templatePath.append(templateProperties.getSuffix());
		return templatePath.toString();
	}

}
