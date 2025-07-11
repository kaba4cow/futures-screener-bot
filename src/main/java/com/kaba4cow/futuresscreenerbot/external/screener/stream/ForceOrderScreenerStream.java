package com.kaba4cow.futuresscreenerbot.external.screener.stream;

import org.springframework.stereotype.Component;

@Component
public class ForceOrderScreenerStream implements ScreenerStream {

	@Override
	public String getSuffix() {
		return "forceOrder";
	}

}
