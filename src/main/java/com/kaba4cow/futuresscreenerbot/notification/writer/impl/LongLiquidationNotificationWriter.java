package com.kaba4cow.futuresscreenerbot.notification.writer.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.notification.impl.TextNotification;
import com.kaba4cow.futuresscreenerbot.notification.writer.NotificationWriter;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LongLiquidationNotificationWriter implements NotificationWriter {

	private final TemplateService templateService;

	@Override
	public TextNotification createNotification(Event event, long eventCount) {
		BigDecimal rounded = event.getValue().setScale(0, RoundingMode.HALF_UP);
		String text = templateService.evaluateTemplate("events/long-liquidation", Map.of(//
				"baseAsset", event.getSymbol().getBaseAsset(), //
				"quoteAsset", event.getSymbol().getQuoteAsset(), //
				"eventCount", eventCount, //
				"liquidationValue", rounded.toPlainString()//
		));
		return new TextNotification(text);
	}

	@Override
	public EventType getEventType() {
		return EventType.LONG_LIQUIDATION;
	}

}
