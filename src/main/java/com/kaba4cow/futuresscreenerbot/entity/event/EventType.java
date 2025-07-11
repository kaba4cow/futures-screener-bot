package com.kaba4cow.futuresscreenerbot.entity.event;

import com.kaba4cow.futuresscreenerbot.tool.Symbol;

public enum EventType {

	PUMP, //

	DUMP, //

	LONG_LIQUIDATION, //

	SHORT_LIQUIDATION;

	public EventSignature signatureFor(Symbol symbol) {
		return new EventSignature(this, symbol);
	}

}
