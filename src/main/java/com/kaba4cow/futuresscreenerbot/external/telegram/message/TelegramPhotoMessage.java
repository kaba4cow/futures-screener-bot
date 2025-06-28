package com.kaba4cow.futuresscreenerbot.external.telegram.message;

import java.util.Set;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import lombok.Getter;

@Getter
public class TelegramPhotoMessage extends TelegramMessage {

	private final SendPhoto photo;

	public TelegramPhotoMessage(Set<Long> chatIds, SendPhoto photo) {
		super(chatIds);
		this.photo = photo;
	}

	public TelegramPhotoMessage(Long chatId, SendPhoto message) {
		this(Set.of(chatId), message);
	}

	public TelegramPhotoMessage(SendPhoto message) {
		this(Long.valueOf(message.getChatId()), message);
	}

}
