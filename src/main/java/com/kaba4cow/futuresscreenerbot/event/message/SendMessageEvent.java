package com.kaba4cow.futuresscreenerbot.event.message;

import java.util.Set;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import lombok.Getter;

@Getter
public class SendMessageEvent extends TelegramMessageEvent {

	private final SendMessage message;

	public SendMessageEvent(Set<Long> chatIds, SendMessage message) {
		super(chatIds);
		this.message = message;
	}

	public SendMessageEvent(Long chatId, SendMessage message) {
		this(Set.of(chatId), message);
	}

}
