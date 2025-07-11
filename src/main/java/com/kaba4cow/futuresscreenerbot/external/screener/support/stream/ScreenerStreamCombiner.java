package com.kaba4cow.futuresscreenerbot.external.screener.support.stream;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.binance.connector.futures.client.WebsocketClient;
import com.kaba4cow.futuresscreenerbot.external.screener.support.ScreenerRegistry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ScreenerStreamCombiner {

	private final WebsocketClient webSocketClient;

	private final ScreenerRegistry screenerRegistry;

	private final ScreenerStreamDataReceiver streamDataReceiver;

	public void combineStreams() {
		webSocketClient.combineStreams(new ArrayList<>(screenerRegistry.getAllStreams()), streamDataReceiver);
	}

}
