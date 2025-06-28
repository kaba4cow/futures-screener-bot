package com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.commandhandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.entity.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramMessage;

public interface CommandHandler {

	@Autowired
	default void registerSelf(CommandHandlerRegistry registry) {
		registry.register(this);
	}

	TelegramMessage getResponseMessage(Subscriber subscriber);

	Command getCommand();

}
