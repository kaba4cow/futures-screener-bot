package com.kaba4cow.futuresscreenerbot.event.message;

import java.util.Set;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public abstract class TelegramMessageEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private final Set<Long> chatIds;

	public TelegramMessageEvent(Object source, Set<Long> chatIds) {
		super(source);
		this.chatIds = chatIds;
	}

}
