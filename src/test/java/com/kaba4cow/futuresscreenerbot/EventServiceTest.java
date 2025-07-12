package com.kaba4cow.futuresscreenerbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kaba4cow.futuresscreenerbot.domain.Symbol;
import com.kaba4cow.futuresscreenerbot.domain.event.Event;
import com.kaba4cow.futuresscreenerbot.domain.event.EventService;
import com.kaba4cow.futuresscreenerbot.domain.event.EventSignature;
import com.kaba4cow.futuresscreenerbot.domain.event.EventType;

@SpringBootTest
class EventServiceTest {

	@Autowired
	private EventService eventService;

	@BeforeEach
	void deleteSubscribers() {
		eventService.deleteAllEvents();
	}

	@Test
	void testEventRegistration() {
		Symbol symbol = new Symbol("BTC", "USDT");
		EventSignature signature = EventType.PUMP.signatureFor(symbol);
		Event event = eventService.registerEvent(signature, 5.0);
		assertNotNull(event);
		assertEquals(signature, event.getSignature());
		assertEquals(5.0, event.getValue());
	}

	@Test
	void testEventFiltering() {
		Symbol btcSymbol = new Symbol("BTC", "USDT");
		Symbol ethSymbol = new Symbol("ETH", "USDT");

		assertNotNull(eventService.registerEvent(EventType.PUMP.signatureFor(btcSymbol), 5.0));

		LocalDateTime thresholdTime = LocalDateTime.now();

		assertNotNull(eventService.registerEvent(EventType.PUMP.signatureFor(btcSymbol), 5.0));
		assertNotNull(eventService.registerEvent(EventType.PUMP.signatureFor(btcSymbol), 5.0));
		assertNotNull(eventService.registerEvent(EventType.PUMP.signatureFor(ethSymbol), 5.0));
		assertNotNull(eventService.registerEvent(EventType.DUMP.signatureFor(ethSymbol), 5.0));

		assertEquals(2, eventService.countEventsBySignature(EventType.PUMP.signatureFor(btcSymbol), thresholdTime));
		assertEquals(1, eventService.countEventsBySignature(EventType.DUMP.signatureFor(ethSymbol), thresholdTime));
	}

}
