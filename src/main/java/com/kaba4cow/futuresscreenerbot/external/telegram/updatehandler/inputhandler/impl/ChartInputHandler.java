package com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.inputhandler.impl;

import java.awt.image.RenderedImage;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramPhotoMessage;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramTextMessage;
import com.kaba4cow.futuresscreenerbot.external.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.inputhandler.InputHandler;
import com.kaba4cow.futuresscreenerbot.properties.screener.ScreenerProperties;
import com.kaba4cow.futuresscreenerbot.service.ChartService;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.tool.ImageInputFileWriter;
import com.kaba4cow.futuresscreenerbot.tool.Symbol;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ChartInputHandler implements InputHandler {

	private final ScreenerProperties screenerProperties;

	private final ChartService chartService;

	private final TemplateService templateService;

	private final ReplyKeyboardFactory replyKeyboardFactory;

	@Override
	public TelegramMessage getResponseMessage(Subscriber subscriber, String input) {
		Symbol symbol = new Symbol(input.toUpperCase(), screenerProperties.getQuoteAsset());
		try {
			RenderedImage chartImage = chartService.createChart(symbol);
			return new TelegramPhotoMessage(SendPhoto.builder()//
					.chatId(subscriber.getId())//
					.photo(ImageInputFileWriter.createInputFile(chartImage))//
					.caption(templateService.evaluateTemplate("messages/chart/chart", Map.of(//
							"symbol", symbol.toSymbolString(), //
							"assets", symbol.toAssetsString()//
					)))//
					.replyMarkup(replyKeyboardFactory.buildMenuKeyboard(subscriber))//
					.build());
		} catch (Exception exception) {
			return new TelegramTextMessage(SendMessage.builder()//
					.chatId(subscriber.getId())//
					.text(templateService.evaluateTemplate("messages/chart/error", Map.of(//
							"symbol", symbol.toSymbolString()//
					)))//
					.replyMarkup(replyKeyboardFactory.buildCancelKeyboard(subscriber))//
					.build());
		}
	}

	@Override
	public Command getCommand() {
		return Command.CHART;
	}

}
