package com.kaba4cow.futuresscreenerbot.infra.telegram.updatehandler.inputhandler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramTextMessage;
import com.kaba4cow.futuresscreenerbot.infra.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.infra.telegram.updatehandler.inputhandler.InputHandler;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;

public abstract class SettingsInputHandler implements InputHandler {

	@Autowired
	private TemplateService templateService;

	@Autowired
	private ReplyKeyboardFactory replyKeyboardFactory;

	public SettingsInputHandler() {}

	@Override
	public TelegramMessage getResponseMessage(Subscriber subscriber, String input) {
		try {
			Double value = Double.valueOf(input);
			if (isOutOfRange(value))
				return new TelegramTextMessage(SendMessage.builder()//
						.chatId(subscriber.getId())//
						.text(templateService.evaluateTemplate("messages/settings/out-of-range"))//
						.replyMarkup(replyKeyboardFactory.buildCancelKeyboard(subscriber))//
						.build());
			setValue(subscriber.getSettings(), value);
			return new TelegramTextMessage(SendMessage.builder()//
					.chatId(subscriber.getId())//
					.text(templateService.evaluateTemplate("messages/settings/value-set"))//
					.replyMarkup(replyKeyboardFactory.buildMenuKeyboard(subscriber))//
					.build());
		} catch (Exception exception) {
			return new TelegramTextMessage(SendMessage.builder()//
					.chatId(subscriber.getId())//
					.text(templateService.evaluateTemplate("messages/settings/invalid"))//
					.replyMarkup(replyKeyboardFactory.buildCancelKeyboard(subscriber))//
					.build());
		}
	}

	protected abstract boolean isOutOfRange(Double value);

	protected abstract void setValue(SubscriberSettings settings, Double value);

}
