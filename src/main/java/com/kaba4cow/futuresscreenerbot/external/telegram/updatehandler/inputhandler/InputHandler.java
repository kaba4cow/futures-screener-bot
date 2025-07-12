package com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.inputhandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramMessage;

public interface InputHandler {

	@Autowired
	default void registerSelf(InputHandlerRegistry registry) {
		registry.register(this);
	}

	TelegramMessage getResponseMessage(Subscriber subscriber, String input);

	Command getCommand();

}
