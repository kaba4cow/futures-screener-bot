package com.kaba4cow.futuresscreenerbot.event;

import java.util.Set;

import org.springframework.context.ApplicationEvent;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import lombok.Getter;

@Getter
public class SendMessageEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private final Set<Long> chatIds;

	private final SendMessage message;

	public SendMessageEvent(Object source, Set<Long> chatIds, SendMessage message) {
		super(source);
		this.chatIds = chatIds;
		this.message = message;
	}

	public SendMessageEvent(Object source, Long chatId, SendMessage message) {
		this(source, Set.of(chatId), message);
	}

}
