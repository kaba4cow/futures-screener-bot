package com.kaba4cow.futuresscreenerbot.external.screener.support;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.ScreenerProperties;
import com.kaba4cow.futuresscreenerbot.external.screener.stream.ScreenerStream;
import com.kaba4cow.futuresscreenerbot.external.screener.stream.support.StreamCombiner;
import com.kaba4cow.futuresscreenerbot.external.screener.stream.support.StreamRegistry;
import com.kaba4cow.futuresscreenerbot.external.screener.stream.support.StreamSymbolRegistry;
import com.kaba4cow.futuresscreenerbot.util.tool.Symbol;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class ScreenerInitializer {

	private final ScreenerProperties screenerProperties;

	private final StreamSymbolRegistry streamSymbolRegistry;

	private final StreamRegistry streamRegistry;

	private final StreamCombiner streamCombiner;

	@PostConstruct
	public void initializeScreeners() {
		Set<Symbol> symbols = collectSymbols();
		Collection<ScreenerStream> streams = streamRegistry.getAllStreams();
		long totalStreams = 0L;
		for (Symbol symbol : symbols)
			for (ScreenerStream stream : streams) {
				String streamName = stream.buildStreamName(symbol);
				streamSymbolRegistry.register(streamName, symbol);
				streamCombiner.addStream(streamName);
				totalStreams++;
			}
		log.info("Total {} streams", totalStreams);
		streamCombiner.combineStreams();
	}

	private Set<Symbol> collectSymbols() {
		String quoteAsset = screenerProperties.getQuoteAsset();
		return screenerProperties.getBaseAssets().stream()//
				.map(baseAsset -> new Symbol(baseAsset, quoteAsset))//
				.collect(Collectors.toSet());
	}

}
