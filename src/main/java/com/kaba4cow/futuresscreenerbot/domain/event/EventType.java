package com.kaba4cow.futuresscreenerbot.domain.event;

import com.kaba4cow.futuresscreenerbot.domain.Symbol;

public enum EventType {

	PUMP, //

	DUMP, //

	LONG_LIQUIDATION, //

	SHORT_LIQUIDATION;

	public EventSignature signatureFor(Symbol symbol) {
		return new EventSignature(this, symbol);
	}

}
