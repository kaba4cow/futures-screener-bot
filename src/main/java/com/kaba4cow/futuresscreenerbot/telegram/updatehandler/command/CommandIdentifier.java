package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CommandIdentifier {

	NONE(false), //

	START(false), //

	CANCEL(false), //

	SUBSCRIBE(false), UNSUBSCRIBE(false), SETTINGS(false), //

	PUMP(true), DUMP(true), SHORT_LIQUIDATION(true), LONG_LIQUIDATION(true);

	private final boolean inputRequired;

}
