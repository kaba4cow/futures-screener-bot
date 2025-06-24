package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberSettings;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.UpdateResponse;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandHandler;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandIdentifier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SettingsCommandHandler implements CommandHandler {

	private final TemplateService templateService;

	@Override
	public UpdateResponse apply(Subscriber subscriber) {
		SubscriberSettings subscriberSettings = subscriber.getSettings();
		return UpdateResponse.builder()//
				.responseText(templateService.evaluateTemplate("messages/settings", Map.of(//
						"status", subscriber.getState().getName(), //
						"pumpThreshold", subscriberSettings.getPumpThreshold(), //
						"dumpThreshold", subscriberSettings.getDumpThreshold(), //
						"longLiquidationThreshold", subscriberSettings.getLongLiquidationThreshold(), //
						"shortLiquidationThreshold", subscriberSettings.getShortLiquidationThreshold()//
				)))//
				.replyKeyboardSupplier(ReplyKeyboardFactory::buildMenuKeyboard)//
				.build();
	}

	@Override
	public CommandIdentifier getCommand() {
		return CommandIdentifier.SETTINGS;
	}

}
