package com.kaba4cow.futuresscreenerbot.infra.screener.registry;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.config.properties.ScreenerProperties;
import com.kaba4cow.futuresscreenerbot.domain.Symbol;
import com.kaba4cow.futuresscreenerbot.infra.stream.ScreenerStream;
import com.kaba4cow.futuresscreenerbot.infra.stream.registry.StreamCombiner;
import com.kaba4cow.futuresscreenerbot.infra.stream.registry.StreamRegistry;
import com.kaba4cow.futuresscreenerbot.infra.stream.registry.StreamSymbolRegistry;

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
