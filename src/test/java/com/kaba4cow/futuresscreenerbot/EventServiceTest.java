package com.kaba4cow.futuresscreenerbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kaba4cow.futuresscreenerbot.entity.event.Event;
import com.kaba4cow.futuresscreenerbot.entity.event.EventSignature;
import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.service.domain.event.EventService;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

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
		EventSignature signature = new EventSignature(EventType.PUMP, symbol);
		Event event = eventService.registerEvent(signature, 5.0);
		assertNotNull(event);
		assertEquals(signature, event.getSignature());
		assertEquals(5.0, event.getValue());
	}

	@Test
	void testEventFiltering() {
		Symbol btcSymbol = new Symbol("BTC", "USDT");
		Symbol ethSymbol = new Symbol("ETH", "USDT");

		assertNotNull(eventService.registerEvent(new EventSignature(EventType.PUMP, btcSymbol), 5.0));

		LocalDateTime thresholdTime = LocalDateTime.now();

		assertNotNull(eventService.registerEvent(new EventSignature(EventType.PUMP, btcSymbol), 5.0));

		assertNotNull(eventService.registerEvent(new EventSignature(EventType.PUMP, btcSymbol), 5.0));

		assertNotNull(eventService.registerEvent(new EventSignature(EventType.PUMP, ethSymbol), 5.0));

		assertNotNull(eventService.registerEvent(new EventSignature(EventType.DUMP, ethSymbol), 5.0));

		assertEquals(2, eventService.countEventsBySignature(new EventSignature(//
				EventType.PUMP, //
				btcSymbol//
		), thresholdTime));

		assertEquals(1, eventService.countEventsBySignature(new EventSignature(//
				EventType.DUMP, //
				ethSymbol//
		), thresholdTime));
	}

}
