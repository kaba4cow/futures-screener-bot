package com.kaba4cow.futuresscreenerbot.event.message;

import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class TelegramMessageEvent {

	private final Set<Long> chatIds;

}
