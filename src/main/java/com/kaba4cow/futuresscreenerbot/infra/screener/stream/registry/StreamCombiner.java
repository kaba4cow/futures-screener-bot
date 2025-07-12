package com.kaba4cow.futuresscreenerbot.infra.screener.stream.registry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.binance.connector.futures.client.WebsocketClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class StreamCombiner {

	private final WebsocketClient webSocketClient;

	private final StreamDataReceiver streamDataReceiver;

	private final Set<String> streams = new HashSet<>();

	public void addStream(String stream) {
		streams.add(stream);
	}

	public void combineStreams() {
		webSocketClient.combineStreams(new ArrayList<>(streams), streamDataReceiver);
	}

}
