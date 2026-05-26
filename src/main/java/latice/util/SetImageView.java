package latice.util;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SetImageView{
	public static Button getImageInButton(Button btn, Image image) {
		ImageView iv = new ImageView();
		
		// parameters to select size, use a better quality and use a cache to improve storage
		iv.setImage(image);
		// adding 5 for cleaner effect
		iv.setFitWidth(155);
		iv.setFitHeight(105);
		iv.setPreserveRatio(false);
        iv.setSmooth(true);
        iv.setCache(true);
        
        // On fait en sorte que il n'y ait pas de petite marge dans le bouton
        btn.setGraphic(iv);
        btn.setPadding(new Insets(0, 0, 0, 0));
        
        return btn;
	}
}
