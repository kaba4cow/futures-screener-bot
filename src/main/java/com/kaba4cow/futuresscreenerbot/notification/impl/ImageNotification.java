package com.kaba4cow.futuresscreenerbot.notification.impl;

import java.awt.image.RenderedImage;
import java.util.Set;
import java.util.function.Function;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import com.kaba4cow.futuresscreenerbot.notification.Notification;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramMessage;
import com.kaba4cow.futuresscreenerbot.telegram.message.TelegramPhotoMessage;
import com.kaba4cow.futuresscreenerbot.tool.ImageInputFileWriter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageNotification implements Notification {

	private final RenderedImage image;

	private final String caption;

	@Override
	public Function<Set<Long>, TelegramMessage> prepareEvent() {
		SendPhoto photo = new SendPhoto();
		photo.setCaption(caption);
		photo.setPhoto(ImageInputFileWriter.createInputFile(image));
		return chatIds -> new TelegramPhotoMessage(chatIds, photo);
	}

}
