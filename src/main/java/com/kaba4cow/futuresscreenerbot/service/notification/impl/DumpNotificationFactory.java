package com.kaba4cow.futuresscreenerbot.service.notification.impl;

import java.awt.image.RenderedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import com.kaba4cow.futuresscreenerbot.entity.event.Event;
import com.kaba4cow.futuresscreenerbot.entity.event.EventType;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.external.telegram.message.TelegramPhotoMessage;
import com.kaba4cow.futuresscreenerbot.service.ChartService;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.service.notification.NotificationFactory;
import com.kaba4cow.futuresscreenerbot.util.ImageInputFileWriter;
import com.kaba4cow.futuresscreenerbot.util.Symbol;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DumpNotificationFactory implements NotificationFactory {

	private final TemplateService templateService;

	private final ChartService chartService;

	@Override
	public TelegramMessage createMessage(Set<Long> chatIds, Event event, long eventCount) {
		BigDecimal rounded = new BigDecimal(event.getValue()).setScale(2, RoundingMode.HALF_UP);
		Symbol symbol = event.getSignature().getSymbol();
		String text = templateService.evaluateTemplate("events/dump", Map.of(//
				"symbol", symbol.toSymbolString(), //
				"assets", symbol.toAssetsString(), //
				"eventCount", eventCount, //
				"dumpValue", rounded.toPlainString()//
		));
		RenderedImage chartImage = chartService.createChart(symbol);
		SendPhoto message = new SendPhoto();
		message.setCaption(text);
		message.setPhoto(ImageInputFileWriter.createInputFile(chartImage));
		return new TelegramPhotoMessage(chatIds, message);
	}

	@Override
	public EventType getEventType() {
		return EventType.DUMP;
	}

}
