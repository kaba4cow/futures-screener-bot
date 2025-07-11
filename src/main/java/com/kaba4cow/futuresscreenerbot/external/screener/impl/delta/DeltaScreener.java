package com.kaba4cow.futuresscreenerbot.external.screener.impl.delta;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.ScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.external.screener.impl.AbstractScreener;
import com.kaba4cow.futuresscreenerbot.external.screener.stream.impl.KLineScreenerStream;

public abstract class DeltaScreener<T extends ScreenerSettingsProperties> extends AbstractScreener<T, KLineScreenerStream> {

}
