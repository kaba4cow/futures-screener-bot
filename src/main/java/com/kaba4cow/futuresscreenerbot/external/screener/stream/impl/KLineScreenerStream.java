package com.kaba4cow.futuresscreenerbot.external.screener.stream.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.external.screener.stream.ScreenerStream;

@Component
public class KLineScreenerStream implements ScreenerStream {

	@Override
	public String getSuffix() {
		return "kline_1m";
	}

}
