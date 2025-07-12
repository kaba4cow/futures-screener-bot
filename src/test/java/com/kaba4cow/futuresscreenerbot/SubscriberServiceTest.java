package com.kaba4cow.futuresscreenerbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kaba4cow.futuresscreenerbot.config.properties.SubscriberSettingsProperties;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberService;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberSettingsProvider;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberState;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;

@SpringBootTest
class SubscriberServiceTest {

	@Autowired
	private SubscriberService subscriberService;

	@Autowired
	private SubscriberSettingsProperties subscriberSettingsProperties;

	@BeforeEach
	void deleteSubscribers() {
		subscriberService.deleteAllSubscribers();
	}

	@Test
	void testSubscriberRegistration() {
		Subscriber subscriber = subscriberService.getOrRegisterSubscriber(1L);
		assertNotNull(subscriber);
		assertEquals(1L, subscriber.getId());
		assertEquals(SubscriberState.SUBSCRIBED, subscriber.getState());
		assertEquals(Command.UNKNOWN, subscriber.getLastCommand());

		SubscriberSettings settings = subscriber.getSettings();
		assertNotNull(settings);
		assertSettingsEqual(subscriberSettingsProperties, settings, SubscriberSettingsProvider::getPumpThreshold);
		assertSettingsEqual(subscriberSettingsProperties, settings, SubscriberSettingsProvider::getDumpThreshold);
		assertSettingsEqual(subscriberSettingsProperties, settings, SubscriberSettingsProvider::getLongLiquidationThreshold);
		assertSettingsEqual(subscriberSettingsProperties, settings, SubscriberSettingsProvider::getShortLiquidationThreshold);
	}

	private void assertSettingsEqual(SubscriberSettingsProvider expected, SubscriberSettingsProvider actual,
			Function<SubscriberSettingsProvider, Object> function) {
		assertEquals(function.apply(expected), function.apply(actual));
	}

}
