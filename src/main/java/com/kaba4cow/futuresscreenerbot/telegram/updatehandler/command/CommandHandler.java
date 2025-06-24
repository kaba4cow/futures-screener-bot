package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.UpdateResponse;

public interface CommandHandler {

	@Autowired
	default void registerSelf(CommandHandlerRegistry registry) {
		registry.register(this);
	}

	UpdateResponse apply(Subscriber subscriber);

	CommandIdentifier getCommand();

}
