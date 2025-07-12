package com.kaba4cow.futuresscreenerbot.infra.telegram.updatehandler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.infra.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.infra.telegram.command.CommandResolver;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.infra.telegram.updatehandler.commandhandler.CommandHandler;
import com.kaba4cow.futuresscreenerbot.infra.telegram.updatehandler.commandhandler.CommandHandlerRegistry;
import com.kaba4cow.futuresscreenerbot.infra.telegram.updatehandler.inputhandler.InputHandler;
import com.kaba4cow.futuresscreenerbot.infra.telegram.updatehandler.inputhandler.InputHandlerRegistry;
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
			String text = update.getMessage().getText();
			TelegramMessage message = getResponseMessage(subscriber, text);
			subscriberRepository.save(subscriber);
			telegramMessageService.sendMessage(message);
		}
	}

	private TelegramMessage getResponseMessage(Subscriber subscriber, String text) {
		if (!commandResolver.hasCommand(text) && subscriber.getLastCommand().isInputRequired())
			return getInputResponseMessage(subscriber, text);
		else
			return getCommandResponseMessage(subscriber, text);
	}

	private TelegramMessage getInputResponseMessage(Subscriber subscriber, String text) {
		InputHandler inputHandler = inputHandlerRegistry.getHandler(subscriber.getLastCommand());
		return inputHandler.getResponseMessage(subscriber, text);
	}

	private TelegramMessage getCommandResponseMessage(Subscriber subscriber, String text) {
		Command currentCommand = commandResolver.resolveCommand(text);
		CommandHandler commandHandler = commandHandlerRegistry.getHandler(currentCommand);
		subscriber.setLastCommand(commandHandler.getCommand());
		return commandHandler.getResponseMessage(subscriber);
	}

}
