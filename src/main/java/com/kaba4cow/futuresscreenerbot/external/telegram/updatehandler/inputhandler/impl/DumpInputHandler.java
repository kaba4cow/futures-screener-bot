package com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.inputhandler.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DumpInputHandler extends SettingsInputHandler {

	private final DumpScreenerSettingsProperties dumpScreenerSettingsProperties;

	@Override
	protected boolean isOutOfRange(Double value) {
		return value.doubleValue() < dumpScreenerSettingsProperties.getMinDumpThreshold().doubleValue()
				|| value.doubleValue() > dumpScreenerSettingsProperties.getMaxDumpThreshold().doubleValue();
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
