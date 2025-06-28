package com.kaba4cow.futuresscreenerbot.service.notification.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.service.notification.NotificationFactory;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramTextMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShortLiquidationNotificationFactory implements NotificationFactory {

	private final TemplateService templateService;

	@Override
	public TelegramMessage createMessage(Set<Long> chatIds, Event event, long eventCount) {
		BigDecimal rounded = new BigDecimal(event.getValue()).setScale(0, RoundingMode.HALF_UP);
		String text = templateService.evaluateTemplate("events/short-liquidation", Map.of(//
				"symbol", event.getSymbol().toSymbolString(), //
				"assets", event.getSymbol().toAssetsString(), //
				"eventCount", eventCount, //
				"liquidationValue", rounded.toPlainString()//
		));
		SendMessage message = new SendMessage();
		message.setText(text);
		return new TelegramTextMessage(chatIds, message);
	}

	@Override
	public EventType getEventType() {
		return EventType.SHORT_LIQUIDATION;
	}

}
