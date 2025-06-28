package com.kaba4cow.futuresscreenerbot.service.domain.subscriber;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.service.domain.subscriber.settingsextractor.SubscriberSettingsExtractor;

@Component
public class SubscriberFilter {

	public boolean filter(Subscriber subscriber, SubscriberSettingsExtractor extractor, Event event) {
		Double threshold = extractor.getThreshold(subscriber.getSettings());
		Double value = event.getValue();
		return value.doubleValue() >= threshold.doubleValue();
	}

}
