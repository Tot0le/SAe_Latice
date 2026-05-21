package latice.gui.model;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import latice.model.Factory;
import latice.model.GameBoard;
import latice.model.Position;
import latice.util.ImageLoader;
import latice.util.ImageNotFoundException;
import latice.util.ImagePath;

public class GameBoardIhm extends GridPane {
	private Integer tileSize = 100;
	public GameBoardIhm(GameBoard gameboard) {
		Image backgroundImage = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_SEA.path(), tileSize, tileSize);
		Image image;
		ArrayList<Position> positions = (ArrayList<Position>) Factory.createAllPositions(gameboard.getLength());

		for (Position position : positions) {
			if (gameboard.isSunAt(position)) {
				image = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_SUN.path(), backgroundImage);
			} else if (gameboard.isMoonAt(position)) {
				image = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_MOON.path(), backgroundImage);
			} else {
				image = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_SEA.path(), backgroundImage);
			}
			
			ImageView currentImageViewBackground = new ImageView(image);
			currentImageViewBackground.setFitHeight(50);
			currentImageViewBackground.setPreserveRatio(true);
			this.add(currentImageViewBackground, position.row(), position.column());
		}
		this.setHgap(1);
		this.setVgap(1);
		
		// Set the max size to be able to move it freely
		this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		this.setStyle("-fx-background-color:#053367; -fx-opacity:1;"); // navy blue font
		this.setPadding(new Insets(15));
		
	}
}
