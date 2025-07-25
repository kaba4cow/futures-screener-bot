package com.kaba4cow.futuresscreenerbot.infra.stream.registry;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.binance.connector.futures.client.utils.WebSocketCallback;
import com.kaba4cow.futuresscreenerbot.domain.Symbol;
import com.kaba4cow.futuresscreenerbot.infra.screener.registry.ScreenerRegistry;
import com.kaba4cow.futuresscreenerbot.infra.stream.ScreenerStream;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class StreamDataReceiver implements WebSocketCallback {

	private final StreamRegistry streamRegistry;

	private final StreamSymbolRegistry streamSymbolRegistry;

	private final ScreenerRegistry screenerRegistry;

	@Override
	public void onReceive(String data) {
		JSONObject json = new JSONObject(data);
		String streamData = json.getString("stream");
		JSONObject jsonData = json.getJSONObject("data");
		Symbol symbol = extractSymbol(streamData);
		ScreenerStream stream = extractStream(streamData);
		screenerRegistry.getScreeners(stream).forEach(screener -> screener.update(symbol, jsonData));
	}

	private Symbol extractSymbol(String streamData) {
		return streamSymbolRegistry.getSymbol(streamData);
	}

	private ScreenerStream extractStream(String streamData) {
		int suffixIndex = streamData.lastIndexOf('@') + 1;
		String suffix = streamData.substring(suffixIndex);
		return streamRegistry.getStream(suffix);
	}

}
