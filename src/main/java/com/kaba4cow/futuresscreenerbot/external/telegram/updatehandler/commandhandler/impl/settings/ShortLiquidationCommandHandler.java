package com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.commandhandler.impl.settings;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.config.properties.screener.settings.ShortLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.external.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramTextMessage;
import com.kaba4cow.futuresscreenerbot.external.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.commandhandler.CommandHandler;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShortLiquidationCommandHandler implements CommandHandler {

	private final TemplateService templateService;

	private final ShortLiquidationScreenerSettingsProperties shortLiquidationScreenerSettingsProperties;

	private final ReplyKeyboardFactory replyKeyboardFactory;

	@Override
	public TelegramMessage getResponseMessage(Subscriber subscriber) {
		return new TelegramTextMessage(SendMessage.builder()//
				.chatId(subscriber.getId())//
				.text(templateService.evaluateTemplate("messages/settings/set-value", Map.of(//
						"valueName", "Short Liquidation Threshold", //
						"valueUnit", "$", //
						"min", shortLiquidationScreenerSettingsProperties.getMinThreshold(), //
						"max", shortLiquidationScreenerSettingsProperties.getMaxThreshold()//
				)))//
				.replyMarkup(replyKeyboardFactory.buildCancelKeyboard(subscriber))//
				.build());
	}

	@Override
	public Command getCommand() {
		return Command.SHORT_LIQUIDATION;
	}

}
