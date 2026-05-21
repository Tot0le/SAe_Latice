	package latice.application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import latice.gui.view.GameScene;
import latice.gui.model.GameBoardIhm;
import latice.gui.view.MainMenu;
import latice.model.GameBoard;

@SuppressWarnings("unused")
public class LaticeApplication extends javafx.application.Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = GameScene.createGameScene();
		Scene scene = MainMenu.createMainMenuScene();
		GameBoard gameboard = new GameBoard();
//		GameBoardIhm visualGameboard = new GameBoardIhm(gameboard);
//		root.getChildren().addAll(visualGameboard);
		
//		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Game starting window");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}