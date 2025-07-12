package com.kaba4cow.futuresscreenerbot.domain.subscriber;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.domain.event.Event;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.extractor.SubscriberSettingsExtractor;

@Component
public class SubscriberFilter {

	public boolean filter(Subscriber subscriber, SubscriberSettingsExtractor extractor, Event event) {
		Double threshold = extractor.getThreshold(subscriber.getSettings());
		Double value = event.getValue();
		return value.doubleValue() >= threshold.doubleValue();
	}

}
