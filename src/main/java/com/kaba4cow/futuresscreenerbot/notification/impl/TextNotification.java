package com.kaba4cow.futuresscreenerbot.notification.impl;

import java.util.Set;
import java.util.function.Function;

import org.springframework.context.ApplicationEvent;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.event.SendMessageEvent;
import com.kaba4cow.futuresscreenerbot.notification.Notification;
import com.kaba4cow.futuresscreenerbot.properties.TemplateProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TextNotification implements Notification {

	private final String text;

	@Override
	public Function<Set<Long>, ApplicationEvent> prepareEvent(TemplateProperties templateProperties) {
		SendMessage message = new SendMessage();
		message.setText(text);
		message.disableWebPagePreview();
		message.setParseMode(templateProperties.getParseMode());
		return chatIds -> new SendMessageEvent(this, chatIds, message);
	}

}
