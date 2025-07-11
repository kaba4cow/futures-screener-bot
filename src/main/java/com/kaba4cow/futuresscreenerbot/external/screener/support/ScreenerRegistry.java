package com.kaba4cow.futuresscreenerbot.external.screener.support;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.external.screener.Screener;
import com.kaba4cow.futuresscreenerbot.external.screener.stream.ScreenerStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScreenerRegistry {

	private final Map<ScreenerStream, Set<Screener>> registry = new ConcurrentHashMap<>();

	public void register(Screener screener) {
		if (registry.computeIfAbsent(screener.getStream(), key -> new HashSet<>()).add(screener))
			log.info("Registered {}", screener.getClass().getSimpleName());
	}

	public Set<Screener> getScreeners(ScreenerStream stream) {
		return Collections.unmodifiableSet(registry.get(stream));
	}

}
