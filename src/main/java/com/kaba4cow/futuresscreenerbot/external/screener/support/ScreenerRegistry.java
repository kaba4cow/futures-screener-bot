package com.kaba4cow.futuresscreenerbot.external.screener.support;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.external.screener.Screener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScreenerRegistry {

	private final Map<String, Set<Screener>> registry = new ConcurrentHashMap<>();

	public void register(Screener screener) {
		if (registry.computeIfAbsent(screener.getStream(), key -> new HashSet<>()).add(screener))
			log.info("Registered {} for symbol {}", screener.getClass().getSimpleName(), screener.getSymbol().toAssetsString());
	}

	public Set<String> getAllStreams() {
		return Collections.unmodifiableSet(registry.keySet());
	}

	public Set<Screener> getAllScreeners() {
		return registry.values().stream()//
				.flatMap(Set::stream)//
				.collect(Collectors.toUnmodifiableSet());
	}

	public Set<Screener> getScreeners(String stream) {
		return Collections.unmodifiableSet(registry.get(stream));
	}

	public int totalScreeners() {
		return registry.size();
	}

}
