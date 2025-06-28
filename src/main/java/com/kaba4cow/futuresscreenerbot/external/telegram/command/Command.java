package com.kaba4cow.futuresscreenerbot.external.telegram.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Command {

	UNKNOWN(false), //

	START(false), //

	CANCEL(false), //

	SUBSCRIBE(false), UNSUBSCRIBE(false), SETTINGS(false), //

	CHART(true), //

	PUMP(true), DUMP(true), SHORT_LIQUIDATION(true), LONG_LIQUIDATION(true);

	private final boolean inputRequired;

}
