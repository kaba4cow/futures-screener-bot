package com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.inputhandler.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.LongLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.subscriber.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LongLiquidationInputHandler extends SettingsInputHandler {

	private final LongLiquidationScreenerSettingsProperties longLiquidationScreenerSettingsProperties;

	@Override
	protected boolean isOutOfRange(Double value) {
		return value.doubleValue() < longLiquidationScreenerSettingsProperties.getMinLongLiquidationThreshold().doubleValue()
				|| value.doubleValue() > longLiquidationScreenerSettingsProperties.getMaxLongLiquidationThreshold()
						.doubleValue();
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
