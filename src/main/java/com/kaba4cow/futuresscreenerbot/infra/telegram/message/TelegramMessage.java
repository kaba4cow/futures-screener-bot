package com.kaba4cow.futuresscreenerbot.infra.telegram.message;

import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class TelegramMessage {

	private final Set<Long> chatIds;

}
