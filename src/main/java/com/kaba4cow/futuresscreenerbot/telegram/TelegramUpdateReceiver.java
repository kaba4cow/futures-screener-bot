package com.kaba4cow.futuresscreenerbot.telegram;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.kaba4cow.futuresscreenerbot.entity.Subscriber;
import com.kaba4cow.futuresscreenerbot.event.TelegramUpdateEvent;
import com.kaba4cow.futuresscreenerbot.service.domain.SubscriberService;
import com.kaba4cow.futuresscreenerbot.telegram.updatehandler.UpdateHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class TelegramUpdateReceiver {

	private final SubscriberService subscriberService;

	private final UpdateHandler commandHandler;

	@EventListener
	public void handleReceiveUpdate(TelegramUpdateEvent telegramUpdateEvent) {
		Update update = telegramUpdateEvent.getUpdate();
		if (update.hasMessage()) {
			log.info("Received update [chatId={}]", update.getMessage().getChatId());
			Long id = update.getMessage().getChatId();
			Subscriber subscriber = subscriberService.getOrRegisterSubscriber(id);
			commandHandler.handleCommand(subscriber, update);
		}
	}

}
