package com.kaba4cow.futuresscreenerbot.infra.telegram.sender.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.kaba4cow.futuresscreenerbot.config.properties.TemplateProperties;
import com.kaba4cow.futuresscreenerbot.infra.telegram.core.TelegramBot;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramTextMessage;
import com.kaba4cow.futuresscreenerbot.infra.telegram.sender.TelegramMessageSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class TelegramTextMessageSender implements TelegramMessageSender<TelegramTextMessage> {

	private final TemplateProperties templateProperties;

	private final TelegramBot telegramBot;

	@Override
	public void sendMessage(TelegramTextMessage message) {
		log.info("Executing SendMessage for {} chatIds", message.getChatIds().size());
		message.getChatIds().forEach(chatId -> {
			try {
				SendMessage sendMessage = message.getMessage();
				sendMessage.setChatId(chatId);
				sendMessage.disableWebPagePreview();
				sendMessage.setParseMode(templateProperties.getParseMode());
				telegramBot.execute(sendMessage);
			} catch (TelegramApiException exception) {
				log.error("Could not execute SendMessage for chatId=" + chatId, exception);
			}
		});
	}

	@Override
	public Class<TelegramTextMessage> getMessageClass() {
		return TelegramTextMessage.class;
	}

}
