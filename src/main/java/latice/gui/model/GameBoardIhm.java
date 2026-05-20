package latice.gui.model;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import latice.model.Factory;
import latice.model.GameBoard;
import latice.model.Position;
import latice.util.ImageLoader;

public class GameBoardIhm extends GridPane {
	Image backgroundImage = ImageLoader.loadImage("/images/bg_sea.png");

	public GameBoardIhm(GameBoard gameboard) {
		this.setHgap(10);
		this.setVgap(10);
		
		ArrayList<Position> positions = (ArrayList<Position>) Factory.createAllPositions(gameboard.getLenght());
		System.out.println(positions);
		for (Position position : positions) {
			ImageView currentImageViewBackground = new ImageView(backgroundImage);
			this.add(currentImageViewBackground, position.row(), position.column());
		}
	}
}
