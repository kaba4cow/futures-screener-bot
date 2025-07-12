package com.kaba4cow.futuresscreenerbot.infra.screener;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.domain.event.EventType;
import com.kaba4cow.futuresscreenerbot.infra.screener.registry.ScreenerRegistry;
import com.kaba4cow.futuresscreenerbot.infra.stream.ScreenerStream;
import com.kaba4cow.futuresscreenerbot.util.tool.Symbol;

public interface Screener {

	@Autowired
	default void registerSelf(ScreenerRegistry registry) {
		registry.register(this);
	}

	void update(Symbol symbol, JSONObject jsonData);

	EventType getEventType();

	ScreenerStream getStream();

}
