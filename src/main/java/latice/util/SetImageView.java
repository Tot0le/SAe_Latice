package latice.util;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SetImageView{
	public static Button getImageInButton(Button btn, Image image, int width, int height) {
		ImageView iv = new ImageView();
		
		// parameters to select size, use a better quality and use a cache to improve storage
		iv.setImage(image);
		// adding 5 for cleaner effect
		iv.setFitWidth(width);
		iv.setFitHeight(height);
		iv.setPreserveRatio(false);
        iv.setSmooth(true);
        iv.setCache(true);
        
        // To not have a small space preventing the image to cover the button
        btn.setGraphic(iv);
        btn.setPadding(new Insets(0, 0, 0, 0));     
        return btn;
	}

}
