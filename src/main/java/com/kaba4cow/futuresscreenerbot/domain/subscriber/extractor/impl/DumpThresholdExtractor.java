package com.kaba4cow.futuresscreenerbot.domain.subscriber.extractor.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.domain.event.EventType;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.extractor.SubscriberSettingsExtractor;

@Component
public class DumpThresholdExtractor implements SubscriberSettingsExtractor {

	@Override
	public Double getThreshold(SubscriberSettings settings) {
		return settings.getDumpThreshold();
	}

	@Override
	public EventType getEventType() {
		return EventType.DUMP;
	}

}
