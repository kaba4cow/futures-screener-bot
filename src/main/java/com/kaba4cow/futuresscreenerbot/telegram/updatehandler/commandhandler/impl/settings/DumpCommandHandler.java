package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.impl.settings;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.properties.screener.DumpScreenerSettingsProperties;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.telegram.replykeyboard.ReplyKeyboardFactory;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.UpdateResponse;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.commandhandler.CommandHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DumpCommandHandler implements CommandHandler {

	private final TemplateService templateService;

	private final DumpScreenerSettingsProperties dumpScreenerSettingsProperties;

	@Override
	public UpdateResponse apply(Subscriber subscriber) {
		return UpdateResponse.builder()//
				.responseText(templateService.evaluateTemplate("messages/settings/set-value", Map.of(//
						"valueName", "Dump Threshold", //
						"valueUnit", "%", //
						"min", dumpScreenerSettingsProperties.getMinDumpThreshold(), //
						"max", dumpScreenerSettingsProperties.getMaxDumpThreshold()//
				)))//
				.replyKeyboardSupplier(ReplyKeyboardFactory::buildCancelKeyboard)//
				.build();
	}

	@Override
	public Command getCommand() {
		return Command.DUMP;
	}

}
