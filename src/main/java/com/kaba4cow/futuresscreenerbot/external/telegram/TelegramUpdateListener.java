package com.kaba4cow.futuresscreenerbot.external.telegram;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.kaba4cow.futuresscreenerbot.entity.subscriber.Subscriber;
import com.kaba4cow.futuresscreenerbot.event.TelegramUpdateEvent;
import com.kaba4cow.futuresscreenerbot.external.telegram.updatehandler.UpdateHandler;
import com.kaba4cow.futuresscreenerbot.service.domain.subscriber.SubscriberService;

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
