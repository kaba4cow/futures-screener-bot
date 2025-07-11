package com.kaba4cow.futuresscreenerbot.external.screener;

import org.json.JSONObject;

import com.kaba4cow.futuresscreenerbot.tool.Symbol;

public interface Screener {

	void update(JSONObject jsonData);

	ScreenerType getType();

	default String getStream() {
		return getSymbol().toSymbolString().toLowerCase().concat(getStreamSuffix());
	}

	String getStreamSuffix();

	Symbol getSymbol();

}
