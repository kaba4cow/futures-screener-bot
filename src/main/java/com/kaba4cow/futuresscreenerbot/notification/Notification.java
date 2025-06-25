package com.kaba4cow.futuresscreenerbot.notification;

import java.util.Set;
import java.util.function.Function;

import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;

public interface Notification {

	Function<Set<Long>, TelegramMessage> prepareEvent();

}
