package com.kaba4cow.futuresscreenerbot.external.screener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.binance.connector.futures.client.WebsocketClient;
import com.kaba4cow.futuresscreenerbot.config.properties.screener.ScreenerProperties;
import com.kaba4cow.futuresscreenerbot.external.screener.factory.ScreenerFactory;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class ScreenerStarter {

	private final List<ScreenerFactory> screenerFactories;

	private final WebsocketClient webSocketClient;

	private final ScreenerProperties screenerProperties;

	@PostConstruct
	public void createScreeners() {
		Map<String, Screener> screeners = new HashMap<>();
		collectSymbols()//
				.forEach(symbol -> registerScreenersForSymbol(symbol, screeners));
		log.info("Registered total {} screeners", screeners.size());
		webSocketClient.combineStreams(new ArrayList<>(screeners.keySet()), data -> {
			JSONObject json = new JSONObject(data);
			String streamName = json.getString("stream");
			JSONObject jsonData = json.getJSONObject("data");
			screeners.get(streamName).updateScreener(jsonData);
		});
	}

	public Set<Symbol> collectSymbols() {
		String quoteAsset = screenerProperties.getQuoteAsset();
		return screenerProperties.getBaseAssets().stream()//
				.map(baseAsset -> new Symbol(baseAsset, quoteAsset))//
				.collect(Collectors.toSet());
	}

	private void registerScreenersForSymbol(Symbol symbol, Map<String, Screener> screeners) {
		screenerFactories.forEach(screenerFactory -> registerScreener(screenerFactory.createScreener(symbol), screeners));
	}

	private void registerScreener(Screener screener, Map<String, Screener> screeners) {
		screeners.put(screener.getScreenerStreamName(), screener);
		log.info("Registered {} screener for symbol {}", screener.getScreenerType(), screener.getSymbol().toAssetsString());
	}

}
