package com.kaba4cow.futuresscreenerbot.external.screener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.binance.connector.futures.client.WebsocketClient;
import com.kaba4cow.futuresscreenerbot.config.properties.screener.ScreenerProperties;
import com.kaba4cow.futuresscreenerbot.external.screener.factory.ScreenerFactory;
import com.kaba4cow.futuresscreenerbot.service.FuturesService;
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

	private final FuturesService futuresService;

	private final ScreenerProperties screenerProperties;

	@PostConstruct
	public void createScreeners() {
		Map<String, Screener> screeners = new LinkedHashMap<>();
		futuresService.getSymbols().stream()//
				.filter(this::filterSymbol)//
				.forEach(symbol -> registerScreenersForSymbol(symbol, screeners));
		log.info("Registered total {} screeners", screeners.size());
		webSocketClient.combineStreams(new ArrayList<>(screeners.keySet()), data -> {
			JSONObject json = new JSONObject(data);
			String streamName = json.getString("stream");
			JSONObject jsonData = json.getJSONObject("data");
			screeners.get(streamName).updateScreener(jsonData);
		});
	}

	private boolean filterSymbol(Symbol symbol) {
		return !screenerProperties.getExcludedBaseAssets().contains(symbol.getBaseAsset()) && //
				!screenerProperties.getQuoteAsset().equalsIgnoreCase(symbol.getBaseAsset()) && //
				screenerProperties.getQuoteAsset().equalsIgnoreCase(symbol.getQuoteAsset());
	}

	private void registerScreenersForSymbol(Symbol symbol, Map<String, Screener> screeners) {
		screenerFactories.forEach(screenerFactory -> registerScreener(screenerFactory.createScreener(symbol), screeners));
	}

	private void registerScreener(Screener screener, Map<String, Screener> screeners) {
		screeners.put(screener.getScreenerStreamName(), screener);
		log.info("Registered {} screener for symbol {}/{}", screener.getScreenerType(), screener.getSymbol().getBaseAsset(),
				screener.getSymbol().getQuoteAsset());
	}

}
