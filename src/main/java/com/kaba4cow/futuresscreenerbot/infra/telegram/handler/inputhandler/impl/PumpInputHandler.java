package com.kaba4cow.futuresscreenerbot.infra.telegram.handler.inputhandler.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.PumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.infra.telegram.command.Command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PumpInputHandler extends SettingsInputHandler {

	private final PumpScreenerSettingsProperties pumpScreenerSettingsProperties;

	@Override
	protected boolean isOutOfRange(Double value) {
		return value.doubleValue() < pumpScreenerSettingsProperties.getMinThreshold().doubleValue()
				|| value.doubleValue() > pumpScreenerSettingsProperties.getMaxThreshold().doubleValue();
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
