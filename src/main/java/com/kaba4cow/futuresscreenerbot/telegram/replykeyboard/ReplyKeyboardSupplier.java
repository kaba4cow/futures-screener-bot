package com.kaba4cow.futuresscreenerbot.telegram.replykeyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;

public interface ReplyKeyboardSupplier {

	ReplyKeyboard accept(ReplyKeyboardFactory factory, Subscriber subscriber);

}
