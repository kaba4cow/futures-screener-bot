package com.kaba4cow.futuresscreenerbot.service.telegram;

import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.service.telegram.messagesender.TelegramMessageSenderRegistry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TelegramMessageService {

	private final TelegramMessageSenderRegistry telegramMessageSenderRegistry;

	public void sendMessage(TelegramMessage message) {
		telegramMessageSenderRegistry.getSender(message).sendMessage(message);
	}

}
