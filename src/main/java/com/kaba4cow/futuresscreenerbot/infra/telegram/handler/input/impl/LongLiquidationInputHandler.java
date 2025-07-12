package com.kaba4cow.futuresscreenerbot.infra.telegram.handler.input.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.LongLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.infra.telegram.command.Command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LongLiquidationInputHandler extends SettingsInputHandler {

	private final LongLiquidationScreenerSettingsProperties longLiquidationScreenerSettingsProperties;

	@Override
	protected boolean isOutOfRange(Double value) {
		return value.doubleValue() < longLiquidationScreenerSettingsProperties.getMinThreshold().doubleValue()
				|| value.doubleValue() > longLiquidationScreenerSettingsProperties.getMaxThreshold().doubleValue();
	}

	@Override
	protected void setValue(SubscriberSettings settings, Double value) {
		settings.setLongLiquidationThreshold(value);
	}

	@Override
	public Command getCommand() {
		return Command.LONG_LIQUIDATION;
	}

}
