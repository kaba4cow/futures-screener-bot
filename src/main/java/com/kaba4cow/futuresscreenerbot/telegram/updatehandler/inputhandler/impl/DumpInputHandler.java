package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.properties.screener.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.telegram.command.Command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DumpInputHandler extends SettingsInputHandler {

	private final DumpScreenerSettingsProperties dumpScreenerSettingsProperties;

	@Override
	protected boolean isOutOfRange(BigDecimal value) {
		return value.doubleValue() < dumpScreenerSettingsProperties.getMinDumpThreshold().doubleValue()
				|| value.doubleValue() > dumpScreenerSettingsProperties.getMaxDumpThreshold().doubleValue();
	}

	@Override
	protected void setValue(SubscriberSettings settings, BigDecimal value) {
		settings.setDumpThreshold(value);
	}

	@Override
	public Command getCommand() {
		return Command.DUMP;
	}

}
