package com.kaba4cow.futuresscreenerbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kaba4cow.futuresscreenerbot.entity.event.Event;
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
		Event event = eventService.registerEvent(EventType.PUMP, symbol, 5.0);
		assertNotNull(event);
		assertEquals(EventType.PUMP, event.getType());
		assertEquals(symbol, event.getSymbol());
		assertEquals(5.0, event.getValue());
	}

}
