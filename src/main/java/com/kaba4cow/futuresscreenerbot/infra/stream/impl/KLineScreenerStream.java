package com.kaba4cow.futuresscreenerbot.infra.stream.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.infra.stream.ScreenerStream;

@Component
public class KLineScreenerStream implements ScreenerStream {

	@Override
	public String getSuffix() {
		return "kline_1m";
	}

}
