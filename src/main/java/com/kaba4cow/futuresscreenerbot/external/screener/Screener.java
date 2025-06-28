package com.kaba4cow.futuresscreenerbot.external.screener;

import org.json.JSONObject;

import com.kaba4cow.futuresscreenerbot.tool.Symbol;

public interface Screener {

	void updateScreener(JSONObject jsonData);

	ScreenerType getScreenerType();

	default String getScreenerStreamName() {
		return getSymbol().toSymbolString().toLowerCase().concat(getStreamSuffix());
	}

	String getStreamSuffix();

	Symbol getSymbol();

}
