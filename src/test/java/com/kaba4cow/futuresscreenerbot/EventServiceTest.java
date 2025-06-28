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
		Event btcPumpEvent1 = eventService.registerEvent(new EventSignature(EventType.PUMP, new Symbol("BTC", "USDT")), 5.0);
		assertNotNull(btcPumpEvent1);

		LocalDateTime thresholdTime = LocalDateTime.now();

		Event btcPumpEvent2 = eventService.registerEvent(new EventSignature(EventType.PUMP, new Symbol("BTC", "USDT")), 5.0);
		assertNotNull(btcPumpEvent2);

		Event btcPumpEvent3 = eventService.registerEvent(new EventSignature(EventType.PUMP, new Symbol("BTC", "USDT")), 5.0);
		assertNotNull(btcPumpEvent3);

		Event ethPumpEvent = eventService.registerEvent(new EventSignature(EventType.PUMP, new Symbol("ETH", "USDT")), 5.0);
		assertNotNull(ethPumpEvent);

		Event ethDumpEvent = eventService.registerEvent(new EventSignature(EventType.DUMP, new Symbol("ETH", "USDT")), 5.0);
		assertNotNull(ethDumpEvent);

		assertEquals(2, eventService.countEventsBySignature(new EventSignature(//
				EventType.PUMP, //
				new Symbol("BTC", "USDT")//
		), thresholdTime));

		assertEquals(1, eventService.countEventsBySignature(new EventSignature(//
				EventType.DUMP, //
				new Symbol("ETH", "USDT")//
		), thresholdTime));
	}

}
