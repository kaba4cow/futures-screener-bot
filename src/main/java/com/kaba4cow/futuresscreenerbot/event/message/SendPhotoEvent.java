package com.kaba4cow.futuresscreenerbot.event.message;

import java.util.Set;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import lombok.Getter;

@Getter
public class SendPhotoEvent extends TelegramMessageEvent {

	private static final long serialVersionUID = 1L;

	private final SendPhoto photo;

	public SendPhotoEvent(Object source, Set<Long> chatIds, SendPhoto photo) {
		super(source, chatIds);
		this.photo = photo;
	}

	public SendPhotoEvent(Object source, Long chatId, SendPhoto message) {
		this(source, Set.of(chatId), message);
	}

}
