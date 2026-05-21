package latice.gui.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import latice.gui.model.GameBoardIhm;
import latice.model.GameBoard;

public class GameScene {
	public static Scene createGameScene() {
		BorderPane root = new BorderPane();
		GameBoard gameboard = new GameBoard();
		GameBoardIhm visualGameboard = new GameBoardIhm(gameboard);
		
		
		root.setCenter(visualGameboard);
		
		BorderPane.setAlignment(visualGameboard, Pos.CENTER);
		Scene scene = new Scene(root,1920,1080);
		
		return scene;
	}
}
