package com.kaba4cow.futuresscreenerbot.notification.impl;

import java.util.Set;
import java.util.function.Function;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.notification.Notification;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramTextMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TextNotification implements Notification {

	private final String text;

	@Override
	public Function<Set<Long>, TelegramMessage> prepareEvent() {
		SendMessage message = new SendMessage();
		message.setText(text);
		message.disableWebPagePreview();
		return chatIds -> new TelegramTextMessage(chatIds, message);
	}

}
