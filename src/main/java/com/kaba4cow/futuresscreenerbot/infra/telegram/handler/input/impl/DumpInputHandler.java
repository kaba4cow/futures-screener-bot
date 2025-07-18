package com.kaba4cow.futuresscreenerbot.infra.telegram.handler.input.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.settings.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.infra.telegram.command.Command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DumpInputHandler extends SettingsInputHandler {

	private final DumpScreenerSettingsProperties dumpScreenerSettingsProperties;

	@Override
	protected boolean isOutOfRange(Double value) {
		return value.doubleValue() < dumpScreenerSettingsProperties.getMinThreshold().doubleValue()
				|| value.doubleValue() > dumpScreenerSettingsProperties.getMaxThreshold().doubleValue();
	}

	@Override
	protected void setValue(SubscriberSettings settings, Double value) {
		settings.setDumpThreshold(value);
	}

	@Override
	public Command getCommand() {
		return Command.DUMP;
	}

}
