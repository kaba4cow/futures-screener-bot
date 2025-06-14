package com.kaba4cow.futuresscreenerbot.event;

import java.util.Set;

import org.springframework.context.ApplicationEvent;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import lombok.Getter;

@Getter
public class SendPhotoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private final Set<Long> chatIds;

	private final SendPhoto photo;

	public SendPhotoEvent(Object source, Set<Long> chatIds, SendPhoto photo) {
		super(source);
		this.chatIds = chatIds;
		this.photo = photo;
	}

}
