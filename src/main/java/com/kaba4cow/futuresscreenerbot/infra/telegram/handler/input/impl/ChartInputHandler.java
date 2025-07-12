package com.kaba4cow.futuresscreenerbot.infra.telegram.handler.input.impl;

import java.awt.image.RenderedImage;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import com.kaba4cow.futuresscreenerbot.config.properties.ScreenerProperties;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.infra.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.infra.telegram.handler.input.InputHandler;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramPhotoMessage;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramTextMessage;
import com.kaba4cow.futuresscreenerbot.infra.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.service.ChartService;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.util.tool.ImageInputFileWriter;
import com.kaba4cow.futuresscreenerbot.util.tool.Symbol;

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
					.replyMarkup(replyKeyboardFactory.buildCancelKeyboard(subscriber))//
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
