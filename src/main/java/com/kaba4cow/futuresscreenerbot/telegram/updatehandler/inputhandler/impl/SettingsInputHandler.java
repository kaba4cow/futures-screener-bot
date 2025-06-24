package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.UpdateResponse;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.inputhandler.InputHandler;

public abstract class SettingsInputHandler implements InputHandler {

	@Autowired
	private TemplateService templateService;

	public SettingsInputHandler() {}

	@Override
	public UpdateResponse apply(Subscriber subscriber, String input) {
		try {
			BigDecimal value = new BigDecimal(input);
			if (isOutOfRange(value))
				return UpdateResponse.builder()//
						.responseText(templateService.evaluateTemplate("messages/out-of-range"))//
						.replyKeyboardSupplier(ReplyKeyboardFactory::buildCancelKeyboard)//
						.build();
			setValue(subscriber.getSettings(), value);
			return UpdateResponse.builder()//
					.responseText(templateService.evaluateTemplate("messages/value-set"))//
					.replyKeyboardSupplier(ReplyKeyboardFactory::buildMenuKeyboard)//
					.build();
		} catch (Exception exception) {
			return UpdateResponse.builder()//
					.responseText(templateService.evaluateTemplate("messages/invalid"))//
					.replyKeyboardSupplier(ReplyKeyboardFactory::buildCancelKeyboard)//
					.build();
		}
	}

	protected abstract boolean isOutOfRange(BigDecimal value);

	protected abstract void setValue(SubscriberSettings settings, BigDecimal value);

}
