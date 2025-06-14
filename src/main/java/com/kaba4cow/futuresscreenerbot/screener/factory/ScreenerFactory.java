package com.kaba4cow.futuresscreenerbot.screener.factory;

import com.kaba4cow.futuresscreenerbot.screener.Screener;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

public interface ScreenerFactory {

	Screener createScreener(Symbol symbol);

}
