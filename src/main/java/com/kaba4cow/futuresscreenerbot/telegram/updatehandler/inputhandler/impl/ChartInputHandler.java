package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.impl;

import java.awt.image.RenderedImage;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.properties.screener.ScreenerProperties;
import com.kaba4cow.futuresscreenerbot.service.ChartService;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.UpdateResponse;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandIdentifier;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.InputHandler;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ChartInputHandler implements InputHandler {

	private final ScreenerProperties screenerProperties;

	private final ChartService chartService;

	private final TemplateService templateService;

	@Override
	public UpdateResponse apply(Subscriber subscriber, String input) {
		Symbol symbol = new Symbol(input.toUpperCase(), screenerProperties.getQuoteAsset());
		try {
			RenderedImage chartImage = chartService.createChart(symbol);
			return UpdateResponse.builder()//
					.responseText(templateService.evaluateTemplate("messages/chart/chart", Map.of(//
							"symbol", symbol.toSymbolString(), //
							"assets", symbol.toAssetsString()//
					)))//
					.replyKeyboardSupplier(ReplyKeyboardFactory::buildMenuKeyboard)//
					.build();
		} catch (Exception exception) {
			return UpdateResponse.builder()//
					.responseText(templateService.evaluateTemplate("messages/chart/error", Map.of(//
							"symbol", symbol.toSymbolString()//
					)))//
					.replyKeyboardSupplier(ReplyKeyboardFactory::buildCancelKeyboard)//
					.build();
		}
	}

	@Override
	public CommandIdentifier getCommand() {
		return CommandIdentifier.CHART;
	}

}
