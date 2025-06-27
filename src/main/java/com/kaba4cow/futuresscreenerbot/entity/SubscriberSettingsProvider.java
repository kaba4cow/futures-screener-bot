package com.kaba4cow.futuresscreenerbot.entity;

import java.math.BigDecimal;

public interface SubscriberSettingsProvider {

	BigDecimal getPumpThreshold();

	BigDecimal getDumpThreshold();

	BigDecimal getLongLiquidationThreshold();

	BigDecimal getShortLiquidationThreshold();

}
