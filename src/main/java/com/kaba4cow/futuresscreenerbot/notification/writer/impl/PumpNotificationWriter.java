package com.kaba4cow.futuresscreenerbot.notification.writer.impl;

import java.awt.image.RenderedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.kaba4cow.futuresscreenerbot.entity.Event;
import com.kaba4cow.futuresscreenerbot.entity.EventType;
import com.kaba4cow.futuresscreenerbot.notification.impl.ImageNotification;
import com.kaba4cow.futuresscreenerbot.notification.writer.NotificationWriter;
import com.kaba4cow.futuresscreenerbot.service.ChartService;
import com.kaba4cow.futuresscreenerbot.service.TemplateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PumpNotificationWriter implements NotificationWriter {

	private final TemplateService templateService;

	private final ChartService chartService;

	@Override
	public ImageNotification createNotification(Event event, long eventCount) {
		BigDecimal rounded = event.getValue().setScale(2, RoundingMode.HALF_UP);
		String text = templateService.evaluateTemplate("events/pump", Map.of(//
				"baseAsset", event.getSymbol().getBaseAsset(), //
				"quoteAsset", event.getSymbol().getQuoteAsset(), //
				"eventCount", eventCount, //
				"pumpValue", rounded.toPlainString()//
		));
		RenderedImage chartImage = chartService.createChart(event.getSymbol());
		return new ImageNotification(chartImage, text);
	}

	@Override
	public EventType getEventType() {
		return EventType.PUMP;
	}

}
