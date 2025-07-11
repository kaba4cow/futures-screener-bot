package com.kaba4cow.futuresscreenerbot.external.screener;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.binance.connector.futures.client.WebsocketClient;
import com.kaba4cow.futuresscreenerbot.external.screener.support.ScreenerRegistry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ScreenerStreamCombiner {

	private final WebsocketClient webSocketClient;

	private final ScreenerRegistry screenerRegistry;

	private final StreamDataReceiver dataReceiver;

	public void combineStreams() {
		Set<String> streams = screenerRegistry.getAllStreams();
		webSocketClient.combineStreams(new ArrayList<>(streams), dataReceiver);
	}

}
