package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.UpdateResponse;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.CommandIdentifier;

public interface InputHandler {

	@Autowired
	default void registerSelf(InputHandlerRegistry registry) {
		registry.register(this);
	}

	UpdateResponse apply(Subscriber subscriber, String input);

	CommandIdentifier getCommand();

}
