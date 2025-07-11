package com.kaba4cow.futuresscreenerbot.external.screener.support.stream;

import java.util.Set;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.binance.connector.futures.client.utils.WebSocketCallback;
import com.kaba4cow.futuresscreenerbot.external.screener.Screener;
import com.kaba4cow.futuresscreenerbot.external.screener.support.ScreenerRegistry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ScreenerStreamDataReceiver implements WebSocketCallback {

	private final ScreenerRegistry screenerRegistry;

	@Override
	public void onReceive(String data) {
		JSONObject json = new JSONObject(data);
		String stream = json.getString("stream");
		JSONObject jsonData = json.getJSONObject("data");
		Set<Screener> screeners = screenerRegistry.getScreeners(stream);
		screeners.forEach(screener -> screener.update(jsonData));
	}

}
