package com.kaba4cow.futuresscreenerbot.external.screener;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.external.screener.stream.ScreenerStream;
import com.kaba4cow.futuresscreenerbot.external.screener.support.ScreenerRegistry;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

public interface Screener {

	@Autowired
	default void registerSelf(ScreenerRegistry registry) {
		registry.register(this);
	}

	void update(Symbol symbol, JSONObject jsonData);

	EventType getEventType();

	ScreenerStream getStream();

}
