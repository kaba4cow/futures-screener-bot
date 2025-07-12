package com.kaba4cow.futuresscreenerbot.service;

import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.binance.connector.futures.client.FuturesClient;
import com.kaba4cow.futuresscreenerbot.domain.Symbol;
import com.kaba4cow.futuresscreenerbot.domain.barseries.BarSeries;
import com.kaba4cow.futuresscreenerbot.util.tool.ParametersBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FuturesService {

	private final FuturesClient futuresClient;

	public BarSeries getBarSeries(Symbol symbol, String interval, int barCount) {
		LinkedHashMap<String, Object> parameters = new ParametersBuilder()//
				.set("symbol", symbol.toSymbolString())//
				.set("interval", interval)//
				.set("limit", barCount)//
				.build();
		JSONArray jsonArray = new JSONArray(futuresClient.market().klines(parameters));
		return new BarSeries(jsonArray);
	}

}
