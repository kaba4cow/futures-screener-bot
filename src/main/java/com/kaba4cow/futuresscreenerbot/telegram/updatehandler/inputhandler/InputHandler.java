package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;

public interface InputHandler {

	@Autowired
	default void registerSelf(InputHandlerRegistry registry) {
		registry.register(this);
	}

	TelegramMessage apply(Subscriber subscriber, String input);

	Command getCommand();

}
