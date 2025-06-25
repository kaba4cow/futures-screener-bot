package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramTextMessage;
import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.InputHandler;

public abstract class SettingsInputHandler implements InputHandler {

	@Autowired
	private TemplateService templateService;

	@Autowired
	private ReplyKeyboardFactory replyKeyboardFactory;

	public SettingsInputHandler() {}

	@Override
	public TelegramMessage apply(Subscriber subscriber, String input) {
		try {
			BigDecimal value = new BigDecimal(input);
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

	protected abstract boolean isOutOfRange(BigDecimal value);

	protected abstract void setValue(SubscriberSettings settings, BigDecimal value);

}
