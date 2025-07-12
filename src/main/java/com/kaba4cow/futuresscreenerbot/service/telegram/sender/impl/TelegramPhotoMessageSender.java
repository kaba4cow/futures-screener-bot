package com.kaba4cow.futuresscreenerbot.service.telegram.sender.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.kaba4cow.futuresscreenerbot.config.properties.TemplateProperties;
import com.kaba4cow.futuresscreenerbot.infra.telegram.TelegramBot;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramPhotoMessage;
import com.kaba4cow.futuresscreenerbot.service.telegram.sender.TelegramMessageSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class TelegramPhotoMessageSender implements TelegramMessageSender<TelegramPhotoMessage> {

	private final TemplateProperties templateProperties;

	private final TelegramBot telegramBot;

	@Override
	public void sendMessage(TelegramPhotoMessage message) {
		log.info("Executing SendPhoto for {} chatIds", message.getChatIds().size());
		message.getChatIds().forEach(chatId -> {
			try {
				SendPhoto sendPhoto = message.getPhoto();
				sendPhoto.setChatId(chatId);
				sendPhoto.setParseMode(templateProperties.getParseMode());
				telegramBot.execute(sendPhoto);
			} catch (TelegramApiException exception) {
				log.error("Could not execute SendPhoto for chatId=" + chatId, exception);
			}
		});
	}

	@Override
	public Class<TelegramPhotoMessage> getMessageClass() {
		return TelegramPhotoMessage.class;
	}

}
