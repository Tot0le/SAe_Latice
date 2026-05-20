package latice.application;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
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
		
		// Set the gameboard in the middle
		visualGameboard.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		visualGameboard.setStyle("-fx-background-color:#00ff00; -fx-opacity:1;"); //debug color
		root.setCenter(visualGameboard);
		
		BorderPane.setAlignment(visualGameboard, Pos.CENTER);
		Scene scene = new Scene(root,1920,1080);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
