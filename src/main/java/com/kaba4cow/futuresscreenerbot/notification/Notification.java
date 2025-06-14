package com.kaba4cow.futuresscreenerbot.notification;

import java.util.Set;
import java.util.function.Function;

import org.springframework.context.ApplicationEvent;

import com.kaba4cow.futuresscreenerbot.properties.telegram.TelegramProperties;

public interface Notification {

	Function<Set<Long>, ApplicationEvent> prepareEvent(TelegramProperties telegramProperties);

}
