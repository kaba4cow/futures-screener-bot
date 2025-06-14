package com.kaba4cow.futuresscreenerbot.tool;

import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.telegram.telegrambots.meta.api.objects.InputFile;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageInputFileWriter {

	@SneakyThrows
	public static InputFile createInputFile(RenderedImage image) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(image, "PNG", output);
		InputStream input = new ByteArrayInputStream(output.toByteArray());
		String filename = "image_".concat(UUID.randomUUID().toString());
		return new InputFile(input, filename);
	}

}
