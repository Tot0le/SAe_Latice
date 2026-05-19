package latice.gui.model;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import latice.model.Factory;
import latice.model.GameBoard;
import latice.model.Position;

public class GameBoardIhm extends GridPane {
	Image backgroundImage = new Image(getClass().getResource("/images/bg_sea.png").toExternalForm());
	ImageView imageViewBackground = new ImageView(backgroundImage);

	public GameBoardIhm(GameBoard gameboard) {
		this.setHgap(100);
		this.setVgap(100);
		this.setGridLinesVisible(true);
		
		
		ArrayList<Position> positions = (ArrayList<Position>) Factory.createAllPositions(gameboard.getLenght());
		for (Position position : positions) {
			this.add(imageViewBackground, position.row(), position.column());
		}
	}
}
