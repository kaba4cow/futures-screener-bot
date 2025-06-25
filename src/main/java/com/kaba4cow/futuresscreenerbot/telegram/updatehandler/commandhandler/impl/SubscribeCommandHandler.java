package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.impl;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberState;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.UpdateResponse;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.CommandHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SubscribeCommandHandler implements CommandHandler {

	private final TemplateService templateService;

	@Override
	public UpdateResponse apply(Subscriber subscriber) {
		subscriber.setState(SubscriberState.SUBSCRIBED);
		return UpdateResponse.builder()//
				.responseText(templateService.evaluateTemplate("messages/subscribe"))//
				.replyKeyboardSupplier(ReplyKeyboardFactory::buildMenuKeyboard)//
				.build();
	}

	@Override
	public Command getCommand() {
		return Command.SUBSCRIBE;
	}

}
