package com.kaba4cow.futuresscreenerbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kaba4cow.futuresscreenerbot.domain.Symbol;
import com.kaba4cow.futuresscreenerbot.domain.event.Event;
import com.kaba4cow.futuresscreenerbot.domain.event.EventSignature;
import com.kaba4cow.futuresscreenerbot.domain.event.EventType;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberRepository;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberService;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberState;

@SpringBootTest
class SubscriberFilteringTest {

	@Autowired
	private SubscriberService subscriberService;

	@Autowired
	private SubscriberRepository subscriberRepository;

	@BeforeEach
	void deleteSubscribers() {
		subscriberService.deleteAllSubscribers();
	}

	@Test
	void testStateFiltering() {
		final int subscribedCount = 4;
		final int unsubscribedCount = 2;
		final int totalCount = subscribedCount + unsubscribedCount;

		IdGenerator idGenerator = new IdGenerator();
		for (int i = 0; i < subscribedCount; i++)
			registerSubscriber(idGenerator, SubscriberState.SUBSCRIBED, settings -> {});
		for (int i = 0; i < unsubscribedCount; i++)
			registerSubscriber(idGenerator, SubscriberState.UNSUBSCRIBED, settings -> {});

		assertEquals(totalCount, subscriberRepository.findAll().size());
		assertEquals(subscribedCount, subscriberRepository.findAllSubscribersByState(SubscriberState.SUBSCRIBED).size());
		assertEquals(unsubscribedCount, subscriberRepository.findAllSubscribersByState(SubscriberState.UNSUBSCRIBED).size());
	}

	@Test
	void testEventFiltering() {
		IdGenerator idGenerator = new IdGenerator();
		registerSubscriber(idGenerator, SubscriberState.SUBSCRIBED, settings -> {
			settings.setPumpThreshold(5.0);
			settings.setDumpThreshold(5.0);
			settings.setLongLiquidationThreshold(5000.0);
			settings.setShortLiquidationThreshold(5000.0);
		});
		registerSubscriber(idGenerator, SubscriberState.SUBSCRIBED, settings -> {
			settings.setPumpThreshold(10.0);
			settings.setDumpThreshold(10.0);
			settings.setLongLiquidationThreshold(10000.0);
			settings.setShortLiquidationThreshold(10000.0);
		});
		registerSubscriber(idGenerator, SubscriberState.SUBSCRIBED, settings -> {
			settings.setPumpThreshold(25.0);
			settings.setDumpThreshold(25.0);
			settings.setLongLiquidationThreshold(25000.0);
			settings.setShortLiquidationThreshold(25000.0);
		});
		registerSubscriber(idGenerator, SubscriberState.SUBSCRIBED, settings -> {
			settings.setPumpThreshold(50.0);
			settings.setDumpThreshold(50.0);
			settings.setLongLiquidationThreshold(50000.0);
			settings.setShortLiquidationThreshold(50000.0);
		});

		assertSubscribersForEventEquals(0, EventType.PUMP, 0.0);
		assertSubscribersForEventEquals(1, EventType.PUMP, 5.0);
		assertSubscribersForEventEquals(2, EventType.PUMP, 10.0);
		assertSubscribersForEventEquals(3, EventType.PUMP, 25.0);
		assertSubscribersForEventEquals(4, EventType.PUMP, 50.0);

		assertSubscribersForEventEquals(0, EventType.DUMP, 0.0);
		assertSubscribersForEventEquals(1, EventType.DUMP, 5.0);
		assertSubscribersForEventEquals(2, EventType.DUMP, 10.0);
		assertSubscribersForEventEquals(3, EventType.DUMP, 25.0);
		assertSubscribersForEventEquals(4, EventType.DUMP, 50.0);

		assertSubscribersForEventEquals(0, EventType.LONG_LIQUIDATION, 0.0);
		assertSubscribersForEventEquals(1, EventType.LONG_LIQUIDATION, 5000.0);
		assertSubscribersForEventEquals(2, EventType.LONG_LIQUIDATION, 10000.0);
		assertSubscribersForEventEquals(3, EventType.LONG_LIQUIDATION, 25000.0);
		assertSubscribersForEventEquals(4, EventType.LONG_LIQUIDATION, 50000.0);

		assertSubscribersForEventEquals(0, EventType.SHORT_LIQUIDATION, 0.0);
		assertSubscribersForEventEquals(1, EventType.SHORT_LIQUIDATION, 5000.0);
		assertSubscribersForEventEquals(2, EventType.SHORT_LIQUIDATION, 10000.0);
		assertSubscribersForEventEquals(3, EventType.SHORT_LIQUIDATION, 25000.0);
		assertSubscribersForEventEquals(4, EventType.SHORT_LIQUIDATION, 50000.0);
	}

	private void assertSubscribersForEventEquals(int expected, EventType eventType, Double eventValue) {
		Event event = createTestEvent(eventType, eventValue);
		List<Subscriber> subscribers = subscriberService.getSubscribersForEvent(event);
		assertEquals(expected, subscribers.size());
	}

	private Event createTestEvent(EventType type, Double value) {
		EventSignature signature = type.signatureFor(new Symbol("BTC", "USDT"));
		Event event = new Event();
		event.setSignature(signature);
		event.setValue(value);
		return event;
	}

	private void registerSubscriber(IdGenerator idGenerator, SubscriberState state,
			Consumer<SubscriberSettings> settingsEditor) {
		Subscriber subscriber = subscriberService.getOrRegisterSubscriber(idGenerator.next());
		assertNotNull(subscriber);
		subscriber.setState(state);
		settingsEditor.accept(subscriber.getSettings());
		subscriberRepository.save(subscriber);
	}

	private static class IdGenerator {

		private long id = 1L;

		private long next() {
			return id++;
		}

	}

}
