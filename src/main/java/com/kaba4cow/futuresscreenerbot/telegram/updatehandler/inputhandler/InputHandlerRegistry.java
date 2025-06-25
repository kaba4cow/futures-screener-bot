package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.CommandIdentifier;

@Component
public class InputHandlerRegistry {

	private final Map<CommandIdentifier, InputHandler> handlers = new ConcurrentHashMap<>();

	public void register(InputHandler handler) {
		handlers.put(handler.getCommand(), handler);
	}

	public InputHandler getHandler(CommandIdentifier command) {
		return handlers.get(command);
	}

}
