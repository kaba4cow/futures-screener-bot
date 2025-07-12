package com.kaba4cow.futuresscreenerbot.domain.subscriber;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SubscriberState {

	SUBSCRIBED("Subscribed"), UNSUBSCRIBED("Unsubscribed");

	private final String name;

}
