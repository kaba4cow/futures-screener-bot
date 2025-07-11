package com.kaba4cow.futuresscreenerbot.external.screener.stream;

import org.springframework.stereotype.Component;

@Component
public class KLineScreenerStream implements ScreenerStream {

	@Override
	public String getSuffix() {
		return "kline_1m";
	}

}
