package com.kaba4cow.futuresscreenerbot.domain.subscriber;

public interface SubscriberSettingsProvider {

	Double getPumpThreshold();

	Double getDumpThreshold();

	Double getLongLiquidationThreshold();

	Double getShortLiquidationThreshold();

}
