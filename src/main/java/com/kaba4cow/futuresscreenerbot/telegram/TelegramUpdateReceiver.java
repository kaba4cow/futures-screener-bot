package com.kaba4cow.futuresscreenerbot.telegram;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.event.TelegramUpdateEvent;
import com.kaba4cow.futuresscreenerbot.service.domain.SubscriberService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class TelegramUpdateReceiver {

	private final SubscriberService subscriberService;

	private final ApplicationEventPublisher applicationEventPublisher;

	@EventListener
	public void handleReceiveUpdate(TelegramUpdateEvent telegramUpdateEvent) {
		Update update = telegramUpdateEvent.getUpdate();
		if (update.hasMessage() && update.getMessage().hasText()) {
			log.info("Received update [chatId={}]", update.getMessage().getChatId());
			Long id = update.getMessage().getChatId();
			Subscriber subscriber = subscriberService.getOrRegisterSubscriber(id);
			String text = update.getMessage().getText();
			new TelegramCommandProcessor(subscriber).processCommand(text);
		}
	}

	@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
	private class TelegramCommandProcessor {

		private final Subscriber subscriber;

		public void processCommand(String text) {

		}

	}

}
