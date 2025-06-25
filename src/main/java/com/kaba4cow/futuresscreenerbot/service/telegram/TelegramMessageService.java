package com.kaba4cow.futuresscreenerbot.service.telegram;

import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.service.telegram.messagesender.TelegramMessageSenderRegistry;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;

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
