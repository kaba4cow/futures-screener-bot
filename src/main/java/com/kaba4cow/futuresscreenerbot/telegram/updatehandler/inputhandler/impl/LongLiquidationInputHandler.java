package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.properties.screener.LongLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandIdentifier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LongLiquidationInputHandler extends SettingsInputHandler {

	private final LongLiquidationScreenerSettingsProperties longLiquidationScreenerSettingsProperties;

	@Override
	protected boolean isOutOfRange(BigDecimal value) {
		return value.doubleValue() < longLiquidationScreenerSettingsProperties.getMinLongLiquidationThreshold().doubleValue()
				|| value.doubleValue() > longLiquidationScreenerSettingsProperties.getMaxLongLiquidationThreshold()
						.doubleValue();
	}

	@Override
	protected void setValue(SubscriberSettings settings, BigDecimal value) {
		settings.setLongLiquidationThreshold(value);
	}

	@Override
	public CommandIdentifier getCommand() {
		return CommandIdentifier.LONG_LIQUIDATION;
	}

}
