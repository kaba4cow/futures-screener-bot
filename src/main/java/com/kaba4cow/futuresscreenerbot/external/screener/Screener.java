package com.kaba4cow.futuresscreenerbot.external.screener;

import org.json.JSONObject;

import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

public interface Screener {

	void update(JSONObject jsonData);

	EventType getEventType();

	default String getStream() {
		return String.format("%s@%s", getSymbol().toSymbolString().toLowerCase(), getStreamSuffix());
	}

	String getStreamSuffix();

	Symbol getSymbol();

}
