package se.hh.imageEditor.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class ImageHandler {

	private static final String IMAGE_FORMAT = "jpg";

	public static BufferedImage readImage(String filename) {
		File file = new File(filename);
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void saveImage(BufferedImage image, File file) {
		try {
			ImageIO.write(image, IMAGE_FORMAT, file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
