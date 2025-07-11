package com.kaba4cow.futuresscreenerbot.external.screener.impl.liquidation;

import org.json.JSONObject;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.ScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.external.screener.impl.AbstractScreener;
import com.kaba4cow.futuresscreenerbot.external.screener.stream.impl.ForceOrderScreenerStream;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

public abstract class LiquidationScreener<T extends ScreenerSettingsProperties>
		extends AbstractScreener<T, ForceOrderScreenerStream> {

	@Override
	public final void update(Symbol symbol, JSONObject jsonData) {
		String side = jsonData.getJSONObject("o").getString("S");
		if (side.equals(getTargetSide())) {
			float price = jsonData.getJSONObject("o").getFloat("p");
			float quantity = jsonData.getJSONObject("o").getFloat("q");
			double liquidation = price * quantity;
			if (liquidation >= getSettingsThreshold())
				registerEvent(symbol, liquidation);
		}
	}

	protected abstract String getTargetSide();

}
