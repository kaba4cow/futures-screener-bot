package com.kaba4cow.futuresscreenerbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.binance.connector.futures.client.FuturesClient;
import com.binance.connector.futures.client.WebsocketClient;
import com.binance.connector.futures.client.impl.UMFuturesClientImpl;
import com.binance.connector.futures.client.impl.UMWebsocketClientImpl;

@Configuration
public class BinanceClientConfig {

	@Bean
	public FuturesClient futuresClient() {
		return new UMFuturesClientImpl();
	}

	@Bean
	public WebsocketClient webSocketClient() {
		return new UMWebsocketClientImpl();
	}

}
