package com.kaba4cow.futuresscreenerbot.infra.telegram.handler.command.impl.settings;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.config.properties.settings.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.infra.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.infra.telegram.handler.command.CommandHandler;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramTextMessage;
import com.kaba4cow.futuresscreenerbot.infra.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DumpCommandHandler implements CommandHandler {

	private final TemplateService templateService;

	private final DumpScreenerSettingsProperties dumpScreenerSettingsProperties;

	private final ReplyKeyboardFactory replyKeyboardFactory;

	@Override
	public TelegramMessage getResponseMessage(Subscriber subscriber) {
		return new TelegramTextMessage(SendMessage.builder()//
				.chatId(subscriber.getId())//
				.text(templateService.evaluateTemplate("messages/settings/set-value", Map.of(//
						"valueName", "Dump Threshold", //
						"valueUnit", "%", //
						"min", dumpScreenerSettingsProperties.getMinThreshold(), //
						"max", dumpScreenerSettingsProperties.getMaxThreshold()//
				)))//
				.replyMarkup(replyKeyboardFactory.buildCancelKeyboard(subscriber))//
				.build());
	}

	@Override
	public Command getCommand() {
		return Command.DUMP;
	}

}
