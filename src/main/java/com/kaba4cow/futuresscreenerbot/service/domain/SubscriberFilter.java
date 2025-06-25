package com.kaba4cow.futuresscreenerbot.service.domain;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.service.domain.settingsextractor.SubscriberSettingsExtractor;

@Component
public class SubscriberFilter {

	public boolean filter(Subscriber subscriber, SubscriberSettingsExtractor extractor, Event event) {
		BigDecimal threshold = extractor.getThreshold(subscriber.getSettings());
		BigDecimal value = event.getValue();
		return value.doubleValue() >= threshold.doubleValue();
	}

}
