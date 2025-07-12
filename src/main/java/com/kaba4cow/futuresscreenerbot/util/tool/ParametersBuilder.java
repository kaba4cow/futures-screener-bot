package com.kaba4cow.futuresscreenerbot.util.tool;

import java.util.LinkedHashMap;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParametersBuilder {

	private final LinkedHashMap<String, Object> map = new LinkedHashMap<>();

	public ParametersBuilder set(String key, Object value) {
		map.put(key, value);
		return this;
	}

	public LinkedHashMap<String, Object> build() {
		return map;
	}

}
