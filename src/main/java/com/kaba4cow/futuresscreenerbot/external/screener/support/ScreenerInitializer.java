package com.kaba4cow.futuresscreenerbot.external.screener.support;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.ScreenerProperties;
import com.kaba4cow.futuresscreenerbot.external.screener.support.factory.ScreenerFactory;
import com.kaba4cow.futuresscreenerbot.external.screener.support.stream.ScreenerStreamCombiner;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ScreenerInitializer {

	private final List<ScreenerFactory> screenerFactories;

	private final ScreenerProperties screenerProperties;

	private final ScreenerRegistry screenerRegistry;

	private final ScreenerStreamCombiner streamCombiner;

	@PostConstruct
	public void initializeScreeners() {
		collectSymbols().forEach(this::registerScreenersForSymbol);
		streamCombiner.combineStreams();
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
