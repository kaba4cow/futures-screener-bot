package com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.CommandResolver;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.commandhandler.CommandHandler;
import com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.commandhandler.CommandHandlerRegistry;
import com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.inputhandler.InputHandler;
import com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.inputhandler.InputHandlerRegistry;
import com.kaba4cow.futuresscreenerbot.repository.SubscriberRepository;
import com.kaba4cow.futuresscreenerbot.service.telegram.TelegramMessageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UpdateHandler {

	private final CommandHandlerRegistry commandHandlerRegistry;

	private final InputHandlerRegistry inputHandlerRegistry;

	private final CommandResolver commandResolver;

	private final SubscriberRepository subscriberRepository;

	private final TelegramMessageService telegramMessageService;

	public void handleUpdate(Subscriber subscriber, Update update) {
		if (update.getMessage().hasText()) {
			String commandText = update.getMessage().getText();
			TelegramMessage message = getResponseMessage(subscriber, commandText);
			subscriberRepository.save(subscriber);
			telegramMessageService.sendMessage(message);
		}
	}

	private TelegramMessage getResponseMessage(Subscriber subscriber, String commandText) {
		Command lastCommand = subscriber.getLastCommand();
		Command currentCommand = commandResolver.resolveCommand(commandText);
		if (Objects.isNull(currentCommand) && lastCommand.isInputRequired()) {
			InputHandler inputHandler = inputHandlerRegistry.getHandler(lastCommand);
			return inputHandler.getResponseMessage(subscriber, commandText);
		} else {
			CommandHandler commandHandler = commandHandlerRegistry.getHandler(currentCommand);
			subscriber.setLastCommand(commandHandler.getCommand());
			return commandHandler.getResponseMessage(subscriber);
		}
	}

}
