package latice.application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import latice.gui.view.GameScene;
import latice.gui.view.MainMenu;
import latice.model.GameBoard;

@SuppressWarnings("unused")
public class LaticeApplication extends javafx.application.Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		Scene scene = GameScene.createGameScene();
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