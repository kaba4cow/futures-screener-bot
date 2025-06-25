package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberState;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramTextMessage;
import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.CommandHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UnsubscribeCommandHandler implements CommandHandler {

	private final TemplateService templateService;

	private final ReplyKeyboardFactory replyKeyboardFactory;

	@Override
	public TelegramMessage apply(Subscriber subscriber) {
		subscriber.setState(SubscriberState.UNSUBSCRIBED);
		return new TelegramTextMessage(SendMessage.builder()//
				.chatId(subscriber.getId())//
				.text(templateService.evaluateTemplate("messages/unsubscribe"))//
				.replyMarkup(replyKeyboardFactory.buildMenuKeyboard(subscriber))//
				.build());
	}

	@Override
	public Command getCommand() {
		return Command.UNSUBSCRIBE;
	}

}
