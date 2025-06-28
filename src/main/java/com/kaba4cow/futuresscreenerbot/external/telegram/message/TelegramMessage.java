package com.kaba4cow.futuresscreenerbot.external.telegram.message;

import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class TelegramMessage {

	private final Set<Long> chatIds;

}
