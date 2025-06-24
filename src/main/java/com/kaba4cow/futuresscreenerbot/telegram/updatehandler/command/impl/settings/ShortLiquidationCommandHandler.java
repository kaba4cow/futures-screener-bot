package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.impl.settings;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.properties.screener.ShortLiquidationScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.UpdateResponse;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandHandler;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandIdentifier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShortLiquidationCommandHandler implements CommandHandler {

	private final TemplateService templateService;

	private final ShortLiquidationScreenerSettingsProperties shortLiquidationScreenerSettingsProperties;

	@Override
	public UpdateResponse apply(Subscriber subscriber) {
		return UpdateResponse.builder()//
				.responseText(templateService.evaluateTemplate("messages/set-value", Map.of(//
						"valueName", "Short Liquidation Threshold", //
						"valueUnit", "$", //
						"min", shortLiquidationScreenerSettingsProperties.getMinShortLiquidationThreshold(), //
						"max", shortLiquidationScreenerSettingsProperties.getMaxShortLiquidationThreshold()//
				)))//
				.replyKeyboardSupplier(ReplyKeyboardFactory::buildCancelKeyboard)//
				.build();
	}

	@Override
	public CommandIdentifier getCommand() {
		return CommandIdentifier.SHORT_LIQUIDATION;
	}

}
