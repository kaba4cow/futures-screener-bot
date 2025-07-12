package com.kaba4cow.futuresscreenerbot.infra.telegram;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.kaba4cow.futuresscreenerbot.domain.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.SubscriberService;
import com.kaba4cow.futuresscreenerbot.infra.telegram.updatehandler.UpdateHandler;
import com.kaba4cow.futuresscreenerbot.service.telegram.TelegramUpdateEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class TelegramUpdateListener {

	private final SubscriberService subscriberService;

	private final UpdateHandler updateHandler;

	@EventListener
	public void handleTelegramUpdateEvent(TelegramUpdateEvent telegramUpdateEvent) {
		Update update = telegramUpdateEvent.getUpdate();
		if (update.hasMessage()) {
			log.info("Received update [chatId={}]", update.getMessage().getChatId());
			Long id = update.getMessage().getChatId();
			Subscriber subscriber = subscriberService.getOrRegisterSubscriber(id);
			updateHandler.handleUpdate(subscriber, update);
		}
	}

}
