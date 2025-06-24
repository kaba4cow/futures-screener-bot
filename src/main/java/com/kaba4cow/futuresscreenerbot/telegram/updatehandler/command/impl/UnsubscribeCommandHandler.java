package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberState;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.UpdateResponse;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandHandler;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandIdentifier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UnsubscribeCommandHandler implements CommandHandler {

	private final TemplateService templateService;

	@Override
	public UpdateResponse apply(Subscriber subscriber) {
		subscriber.setState(SubscriberState.UNSUBSCRIBED);
		return UpdateResponse.builder()//
				.responseText(templateService.evaluateTemplate("messages/unsubscribe"))//
				.replyKeyboardSupplier(ReplyKeyboardFactory::buildMenuKeyboard)//
				.build();
	}

	@Override
	public CommandIdentifier getCommand() {
		return CommandIdentifier.UNSUBSCRIBE;
	}

}
