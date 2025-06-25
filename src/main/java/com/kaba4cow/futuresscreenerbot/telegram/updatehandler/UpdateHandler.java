package com.kaba4cow.futuresscreenerbot.telegram.updatehandler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.repository.SubscriberRepository;
import com.kaba4cow.futuresscreenerbot.service.telegram.TelegramMessageService;
import com.kaba4cow.futuresscreenerbot.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.telegram.command.CommandResolver;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.CommandHandler;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.CommandHandlerRegistry;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.InputHandler;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.InputHandlerRegistry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UpdateHandler {

	private final CommandHandlerRegistry commandHandlerRegistry;

	private final InputHandlerRegistry inputHandlerRegistry;

	private final CommandResolver commandResolver;

	private final SubscriberRepository subscriberRepository;

	private final TelegramMessageService telegramMessageService;

	public void handleCommand(Subscriber subscriber, Update update) {
		if (update.getMessage().hasText()) {
			String commandText = update.getMessage().getText();
			TelegramMessage message = getResponse(subscriber, commandText);
			subscriberRepository.save(subscriber);
			telegramMessageService.sendMessage(message);
		}
	}

	private TelegramMessage getResponse(Subscriber subscriber, String commandText) {
		Command lastCommand = subscriber.getLastCommand();
		Command currentCommand = commandResolver.resolveCommand(commandText);
		if (currentCommand == null && lastCommand.isInputRequired()) {
			InputHandler inputHandler = inputHandlerRegistry.getHandler(lastCommand);
			return inputHandler.apply(subscriber, commandText);
		} else {
			CommandHandler commandHandler = commandHandlerRegistry.getHandler(currentCommand);
			subscriber.setLastCommand(commandHandler.getCommand());
			return commandHandler.apply(subscriber);
		}
	}

}
