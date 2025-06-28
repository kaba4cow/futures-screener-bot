package com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.commandhandler.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.entity.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.subscriber.SubscriberState;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramTextMessage;
import com.kaba4cow.futuresscreenerbot.external.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.commandhandler.CommandHandler;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UnsubscribeCommandHandler implements CommandHandler {

	private final TemplateService templateService;

	private final ReplyKeyboardFactory replyKeyboardFactory;

	@Override
	public TelegramMessage getResponseMessage(Subscriber subscriber) {
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
