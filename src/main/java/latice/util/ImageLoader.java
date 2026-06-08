package latice.util;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import latice.util.exception.ImageNotFoundException;

public class ImageLoader {
	public ImageLoader() {

	}

	public static Image loadImage(String path, int preferedWidth, int preferedHeight) throws ImageNotFoundException {
		Image image = null;

		InputStream imageStream = ImageLoader.class.getResourceAsStream(path);

		if (imageStream == null) {
			throw new ImageNotFoundException("Image with the path " + path + " not found.");
		} else {
			image = new Image(imageStream, preferedWidth, preferedHeight, true, false);
		}
		return image;
	}
	
	public static Image loadImage(String path) throws ImageNotFoundException {
		Image image = null;

		InputStream imageStream = ImageLoader.class.getResourceAsStream(path);

		if (imageStream == null) {
			throw new ImageNotFoundException("Image with the path " + path + " not found.");
		} else {
			image = new Image(imageStream);
		}
		return image;
	}

	public static Image loadImageSafe(String path, int backupImageWidth, int backupImageHeight) {
		Image image;
		try {
			image = loadImage(path, backupImageWidth, backupImageHeight);
		} catch (ImageNotFoundException e) {
			Rectangle fallbackRectangleImage = new Rectangle(backupImageWidth, backupImageHeight, Color.CYAN);

			image = fallbackRectangleImage.snapshot(null, null);
			System.err.println(e.getMessage() + " Backup image applied");
		}

		return image;
	}

	public static Image loadImageSafe(String path, Image backupImage) {
		Image image;
		try {
			image = loadImage(path);
		} catch (ImageNotFoundException e) {
			image = backupImage;
			System.err.println(e.getMessage() + " Backup image applied");
		}

		return image;
	}
}
