package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;

public interface CommandHandler {

	@Autowired
	default void registerSelf(CommandHandlerRegistry registry) {
		registry.register(this);
	}

	TelegramMessage getResponseMessage(Subscriber subscriber);

	Command getCommand();

}
