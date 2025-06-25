package com.kaba4cow.futuresscreenerbot.telegram.replykeyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.entity.SubscriberState;
import com.kaba4cow.futuresscreenerbot.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.telegram.command.CommandResolver;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ReplyKeyboardFactory {

	private final CommandResolver commandResolver;

	public ReplyKeyboard buildMenuKeyboard(Subscriber subscriber) {
		Command changeStateCommand = subscriber.getState() == SubscriberState.SUBSCRIBED//
				? Command.UNSUBSCRIBE//
				: Command.SUBSCRIBE;
		return new ReplyKeyboardBuilder(commandResolver)//
				.row(Command.PUMP, Command.LONG_LIQUIDATION)//
				.row(Command.DUMP, Command.SHORT_LIQUIDATION)//
				.row(Command.CHART, Command.SETTINGS)//
				.row(changeStateCommand)//
				.build();
	}

	public ReplyKeyboard buildCancelKeyboard(Subscriber subscriber) {
		return new ReplyKeyboardBuilder(commandResolver)//
				.row(Command.CANCEL)//
				.build();
	}

}
