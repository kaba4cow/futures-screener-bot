package com.kaba4cow.futuresscreenerbot.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.kaba4cow.futuresscreenerbot.event.TelegramUpdateEvent;
import com.kaba4cow.futuresscreenerbot.properties.TelegramBotProperties;

import jakarta.annotation.PostConstruct;

@Component
public class TelegramBot extends TelegramLongPollingBot {

	private final TelegramBotProperties telegramBotProperties;

	private final ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	public TelegramBot(TelegramBotProperties telegramBotProperties, ApplicationEventPublisher applicationEventPublisher) {
		super(telegramBotProperties.getToken());
		this.telegramBotProperties = telegramBotProperties;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@PostConstruct
	public void registerBot() throws TelegramApiException {
		new TelegramBotsApi(DefaultBotSession.class).registerBot(this);
	}

	@Override
	public void onUpdateReceived(Update update) {
		applicationEventPublisher.publishEvent(new TelegramUpdateEvent(this, update));
	}

	@Override
	public String getBotUsername() {
		return telegramBotProperties.getName();
	}

}
