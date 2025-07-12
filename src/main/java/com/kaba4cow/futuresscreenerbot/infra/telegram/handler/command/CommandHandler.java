package com.kaba4cow.futuresscreenerbot.infra.telegram.handler.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.infra.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramMessage;

public interface CommandHandler {

	@Autowired
	default void registerSelf(CommandHandlerRegistry registry) {
		registry.register(this);
	}

	TelegramMessage getResponseMessage(Subscriber subscriber);

	Command getCommand();

}
