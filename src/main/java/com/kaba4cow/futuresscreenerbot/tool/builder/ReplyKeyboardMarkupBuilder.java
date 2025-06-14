package com.kaba4cow.futuresscreenerbot.tool.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class ReplyKeyboardMarkupBuilder {

	private final List<KeyboardRow> rows;

	public ReplyKeyboardMarkupBuilder() {
		this.rows = new ArrayList<>();
	}

	public ReplyKeyboardMarkupBuilder row(List<String> options) {
		KeyboardRow row = new KeyboardRow();
		options.stream().map(KeyboardButton::new).forEach(row::add);
		rows.add(row);
		return this;
	}

	public ReplyKeyboardMarkup build() {
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
		replyKeyboardMarkup.setResizeKeyboard(true);
		replyKeyboardMarkup.setKeyboard(Collections.unmodifiableList(rows));
		return replyKeyboardMarkup;
	}

}