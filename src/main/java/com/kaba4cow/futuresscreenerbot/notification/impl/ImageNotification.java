package com.kaba4cow.futuresscreenerbot.notification.impl;

import java.awt.image.RenderedImage;
import java.util.Set;
import java.util.function.Function;

import org.springframework.context.ApplicationEvent;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import com.kaba4cow.futuresscreenerbot.event.SendPhotoEvent;
import com.kaba4cow.futuresscreenerbot.notification.Notification;
import com.kaba4cow.futuresscreenerbot.properties.telegram.TelegramProperties;
import com.kaba4cow.futuresscreenerbot.tool.ImageInputFileWriter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageNotification implements Notification {

	private final RenderedImage image;

	private final String caption;

	@Override
	public Function<Set<Long>, ApplicationEvent> prepareEvent(TelegramProperties telegramProperties) {
		SendPhoto photo = new SendPhoto();
		photo.setCaption(caption);
		photo.setPhoto(ImageInputFileWriter.createInputFile(image));
		photo.setParseMode(telegramProperties.getParseMode());
		return chatIds -> new SendPhotoEvent(this, chatIds, photo);
	}

}
