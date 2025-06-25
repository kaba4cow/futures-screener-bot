package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.properties.screener.ShortLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.telegram.command.Command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShortLiquidationInputHandler extends SettingsInputHandler {

	private final ShortLiquidationScreenerSettingsProperties shortLiquidationScreenerSettingsProperties;

	@Override
	protected boolean isOutOfRange(BigDecimal value) {
		return value.doubleValue() < shortLiquidationScreenerSettingsProperties.getMinShortLiquidationThreshold().doubleValue()
				|| value.doubleValue() > shortLiquidationScreenerSettingsProperties.getMaxShortLiquidationThreshold()
						.doubleValue();
	}

	@Override
	protected void setValue(SubscriberSettings settings, BigDecimal value) {
		settings.setShortLiquidationThreshold(value);
	}

	@Override
	public Command getCommand() {
		return Command.SHORT_LIQUIDATION;
	}

}
