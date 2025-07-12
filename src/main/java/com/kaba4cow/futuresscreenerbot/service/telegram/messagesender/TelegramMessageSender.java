package com.kaba4cow.futuresscreenerbot.service.telegram.messagesender;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramMessage;

public interface TelegramMessageSender<T extends TelegramMessage> {

	@Autowired
	default void registerSelf(TelegramMessageSenderRegistry registry) {
		registry.register(this);
	}

	void sendMessage(T message);

	Class<T> getMessageClass();

}
