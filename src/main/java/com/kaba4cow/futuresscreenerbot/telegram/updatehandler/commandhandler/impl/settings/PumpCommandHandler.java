package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.impl.settings;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.properties.screener.PumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.UpdateResponse;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.CommandHandler;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.CommandIdentifier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PumpCommandHandler implements CommandHandler {

	private final TemplateService templateService;

	private final PumpScreenerSettingsProperties pumpScreenerSettingsProperties;

	@Override
	public UpdateResponse apply(Subscriber subscriber) {
		return UpdateResponse.builder()//
				.responseText(templateService.evaluateTemplate("messages/settings/set-value", Map.of(//
						"valueName", "Pump Threshold", //
						"valueUnit", "%", //
						"min", pumpScreenerSettingsProperties.getMinPumpThreshold(), //
						"max", pumpScreenerSettingsProperties.getMaxPumpThreshold()//
				)))//
				.replyKeyboardSupplier(ReplyKeyboardFactory::buildCancelKeyboard)//
				.build();
	}

	@Override
	public CommandIdentifier getCommand() {
		return CommandIdentifier.PUMP;
	}

}
