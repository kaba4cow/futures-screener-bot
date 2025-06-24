package com.kaba4cow.futuresscreenerbot.telegram.updatehandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.event.SendMessageEvent;
import com.kaba4cow.futuresscreenerbot.properties.TemplateProperties;
import com.kaba4cow.futuresscreenerbot.repository.SubscriberRepository;
import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandHandler;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandHandlerRegistry;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandIdentifier;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandResolver;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.InputHandler;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.InputHandlerRegistry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UpdateHandler {

	private final CommandHandlerRegistry commandHandlerRegistry;

	private final InputHandlerRegistry inputHandlerRegistry;

	private final CommandResolver commandIdentifierResolver;

	private final TemplateProperties templateProperties;

	private final ReplyKeyboardFactory replyKeyboardFactory;

	private final SubscriberRepository subscriberRepository;

	private final ApplicationEventPublisher applicationEventPublisher;

	public void handleCommand(Subscriber subscriber, Update update) {
		if (update.getMessage().hasText()) {
			String commandText = update.getMessage().getText();
			UpdateResponse response = getResponse(subscriber, commandText);
			subscriberRepository.save(subscriber);
			sendMessage(subscriber, response);
		}
	}

	private UpdateResponse getResponse(Subscriber subscriber, String commandText) {
		CommandIdentifier lastCommand = subscriber.getLastCommand();
		CommandIdentifier currentCommand = commandIdentifierResolver.resolveIdentifier(commandText);
		if (currentCommand == null && lastCommand.isInputRequired()) {
			InputHandler inputHandler = inputHandlerRegistry.getHandler(lastCommand);
			return inputHandler.apply(subscriber, commandText);
		} else {
			CommandHandler commandHandler = commandHandlerRegistry.getHandler(currentCommand);
			subscriber.setLastCommand(commandHandler.getCommand());
			return commandHandler.apply(subscriber);
		}
	}

	private void sendMessage(Subscriber subscriber, UpdateResponse response) {
		SendMessage message = SendMessage.builder()//
				.chatId(subscriber.getId())//
				.parseMode(templateProperties.getParseMode())//
				.text(response.getResponseText())//
				.replyMarkup(response.getReplyKeyboardSupplier().accept(replyKeyboardFactory, subscriber))//
				.build();
		applicationEventPublisher.publishEvent(new SendMessageEvent(this, subscriber.getId(), message));
	}

}
