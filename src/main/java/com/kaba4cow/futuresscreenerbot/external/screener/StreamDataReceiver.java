package com.kaba4cow.futuresscreenerbot.external.screener;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.binance.connector.futures.client.utils.WebSocketCallback;
import com.kaba4cow.futuresscreenerbot.external.screener.support.ScreenerRegistry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class StreamDataReceiver implements WebSocketCallback {

	private final ScreenerRegistry screenerRegistry;

	@Override
	public void onReceive(String data) {
		JSONObject json = new JSONObject(data);
		String stream = json.getString("stream");
		JSONObject jsonData = json.getJSONObject("data");
		Screener screener = screenerRegistry.getScreener(stream);
		screener.update(jsonData);
	}

}
