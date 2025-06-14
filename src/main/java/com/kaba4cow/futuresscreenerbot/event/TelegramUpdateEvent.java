package com.kaba4cow.futuresscreenerbot.event;

import org.springframework.context.ApplicationEvent;
import org.telegram.telegrambots.meta.api.objects.Update;

import lombok.Getter;

@Getter
public class TelegramUpdateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private final Update update;

	public TelegramUpdateEvent(Object source, Update update) {
		super(source);
		this.update = update;
	}

}
