package latice.gui.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import latice.gui.model.GameBoardIhm;
import latice.model.GameBoard;
import latice.model.Position;
import latice.model.tile.Color;
import latice.model.tile.Shape;
import latice.model.tile.Tile;

public class GameScene {
	public static Scene createGameScene() {
		BorderPane root = new BorderPane();
		GameBoard gameboard = new GameBoard();
		GameBoardIhm visualGameboard = new GameBoardIhm(gameboard);
		gameboard.put(new Position(5,5), new Tile(Color.Green, Shape.Bird));
		visualGameboard.update();
		
		root.setCenter(visualGameboard);
		
		BorderPane.setAlignment(visualGameboard, Pos.CENTER);
		Scene scene = new Scene(root,1920,1080);
		// note that the size of the window does not change if the scene change their size during the program execution
		
		return scene;
	}
}
