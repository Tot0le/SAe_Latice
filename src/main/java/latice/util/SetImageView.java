package latice.util;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SetImageView{
	public static Button getImageInButtonByDefault(Button btn, Image image) {
		ImageView iv = new ImageView();
		
		// parameters to select size, use a better quality and use a cache to improve storage
		iv.setImage(image);
		// adding 5 for cleaner effect
		iv.setFitWidth(155);
		iv.setFitHeight(105);
		iv.setPreserveRatio(false);
        iv.setSmooth(true);
        iv.setCache(true);
        
        // To not have a small space preventing the image to cover the button
        btn.setGraphic(iv);
        btn.setPadding(new Insets(0, 0, 0, 0));     
        return btn;
	}
	
	public static Button getImageInButtonBigger(Button btn, Image image) {
		ImageView iv = new ImageView();
		
		// parameters to select size, use a better quality and use a cache to improve storage
		iv.setImage(image);
		// adding 5 for cleaner effect
		iv.setFitWidth(305);
		iv.setFitHeight(205);
		iv.setPreserveRatio(false);
        iv.setSmooth(true);
        iv.setCache(true);
        
        // To not have a small space preventing the image to cover the button
        btn.setGraphic(iv);
        btn.setPadding(new Insets(0, 0, 0, 0));     
        return btn;
	}
}
