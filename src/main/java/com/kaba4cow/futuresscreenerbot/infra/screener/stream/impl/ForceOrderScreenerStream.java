package com.kaba4cow.futuresscreenerbot.infra.screener.stream.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.infra.screener.stream.ScreenerStream;

@Component
public class ForceOrderScreenerStream implements ScreenerStream {

	@Override
	public String getSuffix() {
		return "forceOrder";
	}

}
