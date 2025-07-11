package com.kaba4cow.futuresscreenerbot.external.screener.stream.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.tool.Symbol;

@Component
public class StreamSymbolRegistry {

	private final Map<String, Symbol> registry = new ConcurrentHashMap<>();

	public void register(String stream, Symbol symbol) {
		registry.put(stream, symbol);
	}

	public Symbol getSymbol(String stream) {
		return registry.get(stream);
	}

}
