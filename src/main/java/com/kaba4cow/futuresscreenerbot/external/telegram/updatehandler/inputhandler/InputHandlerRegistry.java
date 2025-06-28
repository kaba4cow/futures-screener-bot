package com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.inputhandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;

@Component
public class InputHandlerRegistry {

	private final Map<Command, InputHandler> handlers = new ConcurrentHashMap<>();

	public void register(InputHandler handler) {
		handlers.put(handler.getCommand(), handler);
	}

	public InputHandler getHandler(Command command) {
		return handlers.get(command);
	}

}
