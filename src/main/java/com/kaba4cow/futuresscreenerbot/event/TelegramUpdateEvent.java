package com.kaba4cow.futuresscreenerbot.event;

import org.telegram.telegrambots.meta.api.objects.Update;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TelegramUpdateEvent {

	private final Update update;

}
