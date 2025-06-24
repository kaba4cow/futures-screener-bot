package com.kaba4cow.futuresscreenerbot.telegram.replykeyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberState;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandIdentifier;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command.CommandResolver;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ReplyKeyboardFactory {

	private final CommandResolver commandResolver;

	public ReplyKeyboard buildMenuKeyboard(Subscriber subscriber) {
		CommandIdentifier changeStateCommand = subscriber.getState() == SubscriberState.SUBSCRIBED//
				? CommandIdentifier.UNSUBSCRIBE//
				: CommandIdentifier.SUBSCRIBE;
		return new ReplyKeyboardBuilder(commandResolver)//
				.row(CommandIdentifier.PUMP, CommandIdentifier.LONG_LIQUIDATION)//
				.row(CommandIdentifier.DUMP, CommandIdentifier.SHORT_LIQUIDATION)//
				.row(changeStateCommand)//
				.row(CommandIdentifier.SETTINGS)//
				.build();
	}

	public ReplyKeyboard buildCancelKeyboard(Subscriber subscriber) {
		return new ReplyKeyboardBuilder(commandResolver)//
				.row(CommandIdentifier.CANCEL)//
				.build();
	}

}
