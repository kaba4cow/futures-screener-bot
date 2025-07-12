package com.kaba4cow.futuresscreenerbot.domain.barseries;

import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kaba4cow.futuresscreenerbot.util.TimeConversionUtil;

import lombok.Getter;

@Getter
public class Bar {

	private final LocalDateTime openTime;
	private final float openPrice;
	private final float highPrice;
	private final float lowPrice;
	private final float closePrice;

	public Bar(JSONArray jsonArray) {
		this.openTime = TimeConversionUtil.toLocalDateTime(jsonArray.getLong(0));
		this.openPrice = jsonArray.getFloat(1);
		this.highPrice = jsonArray.getFloat(2);
		this.lowPrice = jsonArray.getFloat(3);
		this.closePrice = jsonArray.getFloat(4);
	}

	public Bar(JSONObject jsonObject) {
		this.openTime = TimeConversionUtil.toLocalDateTime(jsonObject.getJSONObject("k").getLong("t"));
		this.openPrice = jsonObject.getJSONObject("k").getFloat("o");
		this.highPrice = jsonObject.getJSONObject("k").getFloat("h");
		this.lowPrice = jsonObject.getJSONObject("k").getFloat("l");
		this.closePrice = jsonObject.getJSONObject("k").getFloat("c");
	}

	public boolean isBullish() {
		return getClosePrice() >= getOpenPrice();
	}

	public boolean isBearish() {
		return !isBullish();
	}

}