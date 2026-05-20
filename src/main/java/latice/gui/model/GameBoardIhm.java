package latice.gui.model;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import latice.model.Factory;
import latice.model.GameBoard;
import latice.model.Position;
import latice.util.ImageLoader;
import latice.util.ImageNotFoundException;
import latice.util.ImagePath;

public class GameBoardIhm extends GridPane {
	private Image backgroundImage = new Image(ImageLoader.class.getResourceAsStream("/images/bg_sea.png"));
	
	public GameBoardIhm(GameBoard gameboard) {
		Image image;
		ArrayList<Position> positions = (ArrayList<Position>) Factory.createAllPositions(gameboard.getLength());

		for (Position position : positions) {
			try {
				//TODO change image if sun square or a moon one
				image = ImageLoader.loadImage(ImagePath.BACKGROUND_SEA.path());
				
			} catch(ImageNotFoundException e) {
				System.err.println("Image not found " + e.getMessage());
				image = backgroundImage;
			}
			
			ImageView currentImageViewBackground = new ImageView(image);
			this.add(currentImageViewBackground, position.row(), position.column());
		}
		this.setHgap(10);
		this.setVgap(10);
		
	}
}
