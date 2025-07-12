package com.kaba4cow.futuresscreenerbot.infra.stream.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.infra.stream.ScreenerStream;

@Component
public class StreamRegistry {

	private final Map<String, ScreenerStream> registry = new ConcurrentHashMap<>();

	public void register(ScreenerStream stream) {
		registry.put(stream.getSuffix(), stream);
	}

	public ScreenerStream getStream(String suffix) {
		return registry.get(suffix);
	}

	public Collection<ScreenerStream> getAllStreams() {
		return Collections.unmodifiableCollection(registry.values());
	}

}
