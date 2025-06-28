package com.kaba4cow.futuresscreenerbot.entity;

public interface SubscriberSettingsProvider {

	Double getPumpThreshold();

	Double getDumpThreshold();

	Double getLongLiquidationThreshold();

	Double getShortLiquidationThreshold();

}
