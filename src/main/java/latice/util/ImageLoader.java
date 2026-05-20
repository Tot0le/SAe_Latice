package latice.util;

import javafx.scene.image.Image;

public class ImageLoader {
	public ImageLoader() {
		
	}
	
	public static Image loadImage(String path) {
		Image image = null;
		
		try {
			image = new Image(ImageLoader.class.getResourceAsStream(path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
		
	}
}
