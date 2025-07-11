package com.kaba4cow.futuresscreenerbot.external.screener;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScreenerRegistry {

	private final Map<String, Screener> registry = new ConcurrentHashMap<>();

	public void register(Screener screener) {
		registry.put(screener.getScreenerStreamName(), screener);
		log.info("Registered {} screener for symbol {}", screener.getScreenerType(), screener.getSymbol().toAssetsString());
	}

	public Set<String> getScreenerStreamNames() {
		return Collections.unmodifiableSet(registry.keySet());
	}

	public Collection<Screener> getAllScreeners() {
		return Collections.unmodifiableCollection(registry.values());
	}

	public Screener getScreener(String streamName) {
		return registry.get(streamName);
	}

	public int totalScreeners() {
		return registry.size();
	}

}
