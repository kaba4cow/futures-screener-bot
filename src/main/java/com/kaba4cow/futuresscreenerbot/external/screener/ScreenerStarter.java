package com.kaba4cow.futuresscreenerbot.external.screener;

import java.util.ArrayList;
import java.util.List;
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

	private final ScreenerRegistry screenerRegistry;

	@PostConstruct
	public void createScreeners() {
		collectSymbols().forEach(this::registerScreenersForSymbol);
		log.info("Registered total {} screeners", screenerRegistry.totalScreeners());
		Set<String> streams = screenerRegistry.getAllStreams();
		webSocketClient.combineStreams(new ArrayList<>(streams), data -> {
			JSONObject json = new JSONObject(data);
			String stream = json.getString("stream");
			JSONObject jsonData = json.getJSONObject("data");
			Screener screener = screenerRegistry.getScreener(stream);
			screener.update(jsonData);
		});
	}

	public Set<Symbol> collectSymbols() {
		String quoteAsset = screenerProperties.getQuoteAsset();
		return screenerProperties.getBaseAssets().stream()//
				.map(baseAsset -> new Symbol(baseAsset, quoteAsset))//
				.collect(Collectors.toSet());
	}

	private void registerScreenersForSymbol(Symbol symbol) {
		screenerFactories.forEach(screenerFactory -> screenerRegistry.register(screenerFactory.createScreener(symbol)));
	}

}
