package com.kaba4cow.futuresscreenerbot.infra.telegram.handler.input;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.infra.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramMessage;

public interface InputHandler {

	@Autowired
	default void registerSelf(InputHandlerRegistry registry) {
		registry.register(this);
	}

	TelegramMessage getResponseMessage(Subscriber subscriber, String input);

	Command getCommand();

}
