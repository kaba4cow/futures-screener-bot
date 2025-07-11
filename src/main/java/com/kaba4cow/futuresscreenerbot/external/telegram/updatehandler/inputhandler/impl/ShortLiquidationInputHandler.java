package com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.inputhandler.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.ShortLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.subscriber.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShortLiquidationInputHandler extends SettingsInputHandler {

	private final ShortLiquidationScreenerSettingsProperties shortLiquidationScreenerSettingsProperties;

	@Override
	protected boolean isOutOfRange(Double value) {
		return value.doubleValue() < shortLiquidationScreenerSettingsProperties.getMinThreshold().doubleValue()
				|| value.doubleValue() > shortLiquidationScreenerSettingsProperties.getMaxThreshold()
						.doubleValue();
	}

	@Override
	protected void setValue(SubscriberSettings settings, Double value) {
		settings.setShortLiquidationThreshold(value);
	}

	@Override
	public Command getCommand() {
		return Command.SHORT_LIQUIDATION;
	}

}
