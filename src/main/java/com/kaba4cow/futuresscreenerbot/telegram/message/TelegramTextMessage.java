package com.kaba4cow.futuresscreenerbot.telegram.message;

import java.util.Set;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import lombok.Getter;

@Getter
public class TelegramTextMessage extends TelegramMessage {

	private final SendMessage message;

	public TelegramTextMessage(Set<Long> chatIds, SendMessage message) {
		super(chatIds);
		this.message = message;
	}

	public TelegramTextMessage(Long chatId, SendMessage message) {
		this(Set.of(chatId), message);
	}

	public TelegramTextMessage(SendMessage message) {
		this(Long.valueOf(message.getChatId()), message);
	}

}
