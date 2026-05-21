package latice.application;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import latice.gui.model.GameBoardIhm;
import latice.model.GameBoard;

public class LaticeApplication extends javafx.application.Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		GameBoard gameboard = new GameBoard();
		GameBoardIhm visualGameboard = new GameBoardIhm(gameboard);
		
		
		root.setCenter(visualGameboard);
		
		BorderPane.setAlignment(visualGameboard, Pos.CENTER);
		Scene scene = new Scene(root,1920,1080);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
