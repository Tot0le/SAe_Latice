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
				if (gameboard.isSunAt(position)) {
					image = ImageLoader.loadImage(ImagePath.BACKGROUND_SUN.path());
				} else if (gameboard.isMoonAt(position)) {
					image = ImageLoader.loadImage(ImagePath.BACKGROUND_MOON.path());
				} else {
					image = ImageLoader.loadImage(ImagePath.BACKGROUND_SEA.path());
				}
				
			} catch(ImageNotFoundException e) {
				System.err.println("Image not found " + e.getMessage());
				image = backgroundImage;
			}

			ImageView currentImageViewBackground = new ImageView(image);
			currentImageViewBackground.setFitHeight(50);
			currentImageViewBackground.setPreserveRatio(true);
			this.add(currentImageViewBackground, position.row(), position.column());
		}
		this.setHgap(2);
		this.setVgap(2);
		
	}
}
