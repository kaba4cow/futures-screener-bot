package com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.commandhandler.impl.settings;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.entity.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramTextMessage;
import com.kaba4cow.futuresscreenerbot.external.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.commandhandler.CommandHandler;
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
						"min", dumpScreenerSettingsProperties.getMinDumpThreshold(), //
						"max", dumpScreenerSettingsProperties.getMaxDumpThreshold()//
				)))//
				.replyMarkup(replyKeyboardFactory.buildCancelKeyboard(subscriber))//
				.build());
	}

	@Override
	public Command getCommand() {
		return Command.DUMP;
	}

}
