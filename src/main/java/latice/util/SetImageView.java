package latice.util;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SetImageView{
	public static Button getImageInButton(Button btn, Image image) {
		ImageView iv = new ImageView();
		
		// parameters to preserve ratio, use a better quality and use a cache to improve storage
		iv.setImage(image);
		iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        
        // 
        btn.setGraphic(iv);
        
        return btn;
	}
}
