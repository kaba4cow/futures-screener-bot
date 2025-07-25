package com.kaba4cow.futuresscreenerbot.infra.telegram.sender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramMessage;

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
