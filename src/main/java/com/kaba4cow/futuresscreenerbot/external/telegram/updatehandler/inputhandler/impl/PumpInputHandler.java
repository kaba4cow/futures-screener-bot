package com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.inputhandler.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.PumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PumpInputHandler extends SettingsInputHandler {

	private final PumpScreenerSettingsProperties pumpScreenerSettingsProperties;

	@Override
	protected boolean isOutOfRange(Double value) {
		return value.doubleValue() < pumpScreenerSettingsProperties.getMinPumpThreshold().doubleValue()
				|| value.doubleValue() > pumpScreenerSettingsProperties.getMaxPumpThreshold().doubleValue();
	}

	@Override
	protected void setValue(SubscriberSettings settings, Double value) {
		settings.setPumpThreshold(value);
	}

	@Override
	public Command getCommand() {
		return Command.PUMP;
	}

}
