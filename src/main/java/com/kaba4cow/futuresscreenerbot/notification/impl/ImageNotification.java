package com.kaba4cow.futuresscreenerbot.notification.impl;

import java.awt.image.RenderedImage;
import java.util.Set;
import java.util.function.Function;

import org.springframework.context.ApplicationEvent;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import com.kaba4cow.futuresscreenerbot.event.message.SendPhotoEvent;
import com.kaba4cow.futuresscreenerbot.notification.Notification;
import com.kaba4cow.futuresscreenerbot.properties.TemplateProperties;
import com.kaba4cow.futuresscreenerbot.tool.ImageInputFileWriter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageNotification implements Notification {

	private final RenderedImage image;

	private final String caption;

	@Override
	public Function<Set<Long>, ApplicationEvent> prepareEvent(TemplateProperties templateProperties) {
		SendPhoto photo = new SendPhoto();
		photo.setCaption(caption);
		photo.setPhoto(ImageInputFileWriter.createInputFile(image));
		photo.setParseMode(templateProperties.getParseMode());
		return chatIds -> new SendPhotoEvent(this, chatIds, photo);
	}

}
