package com.kaba4cow.futuresscreenerbot.event.message;

import java.util.Set;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import lombok.Getter;

@Getter
public class SendPhotoEvent extends TelegramMessageEvent {

	private final SendPhoto photo;

	public SendPhotoEvent(Set<Long> chatIds, SendPhoto photo) {
		super(chatIds);
		this.photo = photo;
	}

	public SendPhotoEvent(Long chatId, SendPhoto message) {
		this(Set.of(chatId), message);
	}

}
