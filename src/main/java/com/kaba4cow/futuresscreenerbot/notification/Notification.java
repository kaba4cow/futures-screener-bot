package com.kaba4cow.futuresscreenerbot.notification;

import java.util.Set;
import java.util.function.Function;

import com.kaba4cow.futuresscreenerbot.event.message.TelegramMessageEvent;
import com.kaba4cow.futuresscreenerbot.properties.TemplateProperties;

public interface Notification {

	Function<Set<Long>, TelegramMessageEvent> prepareEvent(TemplateProperties templateProperties);

}
