package com.kaba4cow.futuresscreenerbot.notificationwriter.impl;

import java.awt.image.RenderedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.notificationwriter.NotificationWriter;
import com.kaba4cow.futuresscreenerbot.service.ChartService;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramPhotoMessage;
import com.kaba4cow.futuresscreenerbot.tool.ImageInputFileWriter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PumpNotificationWriter implements NotificationWriter {

	private final TemplateService templateService;

	private final ChartService chartService;

	@Override
	public TelegramMessage createMessage(Set<Long> chatIds, Event event, long eventCount) {
		BigDecimal rounded = event.getValue().setScale(2, RoundingMode.HALF_UP);
		String text = templateService.evaluateTemplate("events/pump", Map.of(//
				"symbol", event.getSymbol().toSymbolString(), //
				"assets", event.getSymbol().toAssetsString(), //
				"eventCount", eventCount, //
				"pumpValue", rounded.toPlainString()//
		));
		RenderedImage chartImage = chartService.createChart(event.getSymbol());
		SendPhoto message = new SendPhoto();
		message.setCaption(text);
		message.setPhoto(ImageInputFileWriter.createInputFile(chartImage));
		return new TelegramPhotoMessage(chatIds, message);
	}

	@Override
	public EventType getEventType() {
		return EventType.PUMP;
	}

}
