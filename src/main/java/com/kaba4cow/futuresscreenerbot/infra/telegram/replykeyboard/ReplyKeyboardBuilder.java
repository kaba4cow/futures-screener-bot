package com.kaba4cow.futuresscreenerbot.infra.telegram.replykeyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import com.kaba4cow.futuresscreenerbot.infra.telegram.command.Command;
import com.kaba4cow.futuresscreenerbot.infra.telegram.command.CommandResolver;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ReplyKeyboardBuilder {

	private final List<KeyboardRow> rows = new ArrayList<>();

	private final CommandResolver commandResolver;

	ReplyKeyboardBuilder row(Command... options) {
		KeyboardRow row = new KeyboardRow();
		Arrays.stream(options)//
				.map(commandResolver::getCommand)//
				.map(KeyboardButton::new)//
				.forEach(row::add);
		rows.add(row);
		return this;
	}

	ReplyKeyboard build() {
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
		replyKeyboardMarkup.setResizeKeyboard(true);
		replyKeyboardMarkup.setKeyboard(Collections.unmodifiableList(rows));
		return replyKeyboardMarkup;
	}

}