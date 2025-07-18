package com.kaba4cow.futuresscreenerbot.domain.subscriber.notification.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.kaba4cow.futuresscreenerbot.domain.Symbol;
import com.kaba4cow.futuresscreenerbot.domain.event.Event;
import com.kaba4cow.futuresscreenerbot.domain.event.EventType;
import com.kaba4cow.futuresscreenerbot.domain.subscriber.notification.NotificationFactory;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.infra.telegram.message.TelegramTextMessage;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShortLiquidationNotificationFactory implements NotificationFactory {

	private final TemplateService templateService;

	@Override
	public TelegramMessage createMessage(Set<Long> chatIds, Event event, long eventCount) {
		BigDecimal rounded = new BigDecimal(event.getValue()).setScale(0, RoundingMode.HALF_UP);
		Symbol symbol = event.getSignature().getSymbol();
		String text = templateService.evaluateTemplate("events/short-liquidation", Map.of(//
				"symbol", symbol.toSymbolString(), //
				"assets", symbol.toAssetsString(), //
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
