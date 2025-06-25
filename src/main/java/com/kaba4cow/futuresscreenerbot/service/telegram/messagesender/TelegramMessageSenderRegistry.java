package com.kaba4cow.futuresscreenerbot.service.telegram.messagesender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;

@Component
public class TelegramMessageSenderRegistry {

	private final Map<Class<? extends TelegramMessage>, TelegramMessageSender<?>> senders = new ConcurrentHashMap<>();

	void register(TelegramMessageSender<?> sender) {
		senders.put(sender.getMessageClass(), sender);
	}

	@SuppressWarnings("unchecked")
	public <T extends TelegramMessage> TelegramMessageSender<T> getSender(T message) {
		return (TelegramMessageSender<T>) senders.get(message.getClass());
	}

}
