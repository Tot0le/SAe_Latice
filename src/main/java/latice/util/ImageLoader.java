package latice.util;

import java.io.InputStream;

import javafx.scene.image.Image;

public class ImageLoader {
	public ImageLoader() {
		
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
}
