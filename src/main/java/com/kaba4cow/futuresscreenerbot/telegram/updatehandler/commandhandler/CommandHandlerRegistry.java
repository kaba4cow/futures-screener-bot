package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class CommandHandlerRegistry {

	private final Map<CommandIdentifier, CommandHandler> handers = new ConcurrentHashMap<>();

	public void register(CommandHandler command) {
		handers.put(command.getCommand(), command);
	}

	public CommandHandler getHandler(CommandIdentifier identifier) {
		return handers.get(identifier);
	}

}
