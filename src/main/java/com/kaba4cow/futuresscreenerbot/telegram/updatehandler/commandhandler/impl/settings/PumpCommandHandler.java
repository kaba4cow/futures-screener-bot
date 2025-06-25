package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.impl.settings;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.properties.screener.PumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramTextMessage;
import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.CommandHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PumpCommandHandler implements CommandHandler {

	private final TemplateService templateService;

	private final PumpScreenerSettingsProperties pumpScreenerSettingsProperties;

	private final ReplyKeyboardFactory replyKeyboardFactory;

	@Override
	public TelegramMessage getResponseMessage(Subscriber subscriber) {
		return new TelegramTextMessage(SendMessage.builder()//
				.chatId(subscriber.getId())//
				.text(templateService.evaluateTemplate("messages/settings/set-value", Map.of(//
						"valueName", "Pump Threshold", //
						"valueUnit", "%", //
						"min", pumpScreenerSettingsProperties.getMinPumpThreshold(), //
						"max", pumpScreenerSettingsProperties.getMaxPumpThreshold()//
				)))//
				.replyMarkup(replyKeyboardFactory.buildCancelKeyboard(subscriber))//
				.build());
	}

	@Override
	public Command getCommand() {
		return Command.PUMP;
	}

}
