package com.kaba4cow.futuresscreenerbot.event.message;

import java.util.Set;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import lombok.Getter;

@Getter
public class SendMessageEvent extends TelegramMessageEvent {

	private static final long serialVersionUID = 1L;

	private final SendMessage message;

	public SendMessageEvent(Object source, Set<Long> chatIds, SendMessage message) {
		super(source, chatIds);
		this.message = message;
	}

	public SendMessageEvent(Object source, Long chatId, SendMessage message) {
		this(source, Set.of(chatId), message);
	}

}
