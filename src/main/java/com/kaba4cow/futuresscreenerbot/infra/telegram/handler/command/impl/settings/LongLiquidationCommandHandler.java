package com.kaba4cow.futuresscreenerbot.infra.telegram.handler.command.impl.settings;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.LongLiquidationScreenerSettingsProperties;
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
public class LongLiquidationCommandHandler implements CommandHandler {

	private final TemplateService templateService;

	private final LongLiquidationScreenerSettingsProperties longLiquidationScreenerSettingsProperties;

	private final ReplyKeyboardFactory replyKeyboardFactory;

	@Override
	public TelegramMessage getResponseMessage(Subscriber subscriber) {
		return new TelegramTextMessage(SendMessage.builder()//
				.chatId(subscriber.getId())//
				.text(templateService.evaluateTemplate("messages/settings/set-value", Map.of(//
						"valueName", "Long Liquidation Threshold", //
						"valueUnit", "$", //
						"min", longLiquidationScreenerSettingsProperties.getMinThreshold(), //
						"max", longLiquidationScreenerSettingsProperties.getMaxThreshold()//
				)))//
				.replyMarkup(replyKeyboardFactory.buildCancelKeyboard(subscriber))//
				.build());
	}

	@Override
	public Command getCommand() {
		return Command.LONG_LIQUIDATION;
	}

}
