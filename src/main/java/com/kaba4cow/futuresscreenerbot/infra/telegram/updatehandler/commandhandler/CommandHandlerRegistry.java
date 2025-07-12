package com.kaba4cow.futuresscreenerbot.infra.telegram.updatehandler.commandhandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.infra.telegram.command.Command;

@Component
public class CommandHandlerRegistry {

	private final Map<Command, CommandHandler> handers = new ConcurrentHashMap<>();

	public void register(CommandHandler command) {
		handers.put(command.getCommand(), command);
	}

	public CommandHandler getHandler(Command identifier) {
		return handers.get(identifier);
	}

}
