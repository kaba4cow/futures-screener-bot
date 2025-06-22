package com.kaba4cow.futuresscreenerbot.telegram;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.kaba4cow.futuresscreenerbot.event.SendMessageEvent;
import com.kaba4cow.futuresscreenerbot.event.SendPhotoEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class TelegramMessageSender {

	private final TelegramBot telegramBot;

	@EventListener
	public void handleSendMessageNotification(SendMessageEvent event) {
		log.info("Executing SendMessage for {} chatIds", event.getChatIds().size());
		SendMessage message = event.getMessage();
		event.getChatIds().forEach(chatId -> {
			try {
				message.setChatId(chatId);
				telegramBot.execute(message);
			} catch (TelegramApiException exception) {
				log.error("Could not execute SendMessage for chatId={}", chatId);
				log.trace("Caused by exception:", exception);
			}
		});
	}

	@EventListener
	public void handleSendPhotoNotification(SendPhotoEvent event) {
		log.info("Executing SendPhoto for {} chatIds", event.getChatIds().size());
		SendPhoto photo = event.getPhoto();
		event.getChatIds().forEach(chatId -> {
			try {
				photo.setChatId(chatId);
				telegramBot.execute(photo);
			} catch (TelegramApiException exception) {
				log.error("Could not execute SendPhoto for chatId={}", chatId);
				log.trace("Caused by exception:", exception);
			}
		});
	}

}
