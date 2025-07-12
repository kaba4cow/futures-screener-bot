package com.kaba4cow.futuresscreenerbot.infra.screener.stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.infra.screener.stream.registry.StreamRegistry;
import com.kaba4cow.futuresscreenerbot.util.tool.Symbol;

public interface ScreenerStream {

	@Autowired
	default void registerSelf(StreamRegistry registry) {
		registry.register(this);
	}

	default String buildStreamName(Symbol symbol) {
		return String.format("%s@%s", symbol.toSymbolString().toLowerCase(), getSuffix());
	}

	String getSuffix();

}
