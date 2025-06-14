package com.kaba4cow.futuresscreenerbot.service;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.binance.connector.futures.client.FuturesClient;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;
import com.kaba4cow.futuresscreenerbot.tool.barseries.BarSeries;
import com.kaba4cow.futuresscreenerbot.tool.builder.ParametersBuilder;
import com.kaba4cow.futuresscreenerbot.tool.util.JsonUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FuturesService {

	private final FuturesClient futuresClient;

	public Set<Symbol> getSymbols() {
		Set<Symbol> symbols = new HashSet<>();
		JSONArray jsonSymbols = new JSONObject(futuresClient.market().exchangeInfo()).getJSONArray("symbols");
		for (int i = 0; i < jsonSymbols.length(); i++) {
			JSONObject jsonSymbol = jsonSymbols.getJSONObject(i);
			String baseAsset = jsonSymbol.getString("baseAsset");
			String quoteAsset = jsonSymbol.getString("quoteAsset");
			String status = jsonSymbol.getString("status");
			if (!status.equals("TRADING"))
				continue;
			String contractType = jsonSymbol.getString("contractType");
			if (!contractType.equals("PERPETUAL"))
				continue;
			JSONArray jsonOrderTypes = jsonSymbol.getJSONArray("orderTypes");
			if (!JsonUtil.contains(jsonOrderTypes, "LIMIT") || !JsonUtil.contains(jsonOrderTypes, "MARKET"))
				continue;
			symbols.add(new Symbol(baseAsset, quoteAsset));
		}
		return symbols;
	}

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
