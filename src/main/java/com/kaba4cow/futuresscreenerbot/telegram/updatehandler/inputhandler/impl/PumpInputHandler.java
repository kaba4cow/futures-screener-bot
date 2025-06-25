package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.properties.screener.PumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.CommandIdentifier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PumpInputHandler extends SettingsInputHandler {

	private final PumpScreenerSettingsProperties pumpScreenerSettingsProperties;

	@Override
	protected boolean isOutOfRange(BigDecimal value) {
		return value.doubleValue() < pumpScreenerSettingsProperties.getMinPumpThreshold().doubleValue()
				|| value.doubleValue() > pumpScreenerSettingsProperties.getMaxPumpThreshold().doubleValue();
	}

	@Override
	protected void setValue(SubscriberSettings settings, BigDecimal value) {
		settings.setPumpThreshold(value);
	}

	@Override
	public CommandIdentifier getCommand() {
		return CommandIdentifier.PUMP;
	}

}
