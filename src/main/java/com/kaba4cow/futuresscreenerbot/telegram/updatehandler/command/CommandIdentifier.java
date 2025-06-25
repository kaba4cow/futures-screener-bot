package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CommandIdentifier {

	NONE(false), //

	START(false), //

	CANCEL(false), //

	SUBSCRIBE(false), UNSUBSCRIBE(false), SETTINGS(false), //

	CHART(true), //

	PUMP(true), DUMP(true), SHORT_LIQUIDATION(true), LONG_LIQUIDATION(true);

	private final boolean inputRequired;

}
