package latice.application;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import latice.gui.model.GameBoardIhm;
import latice.model.GameBoard;

public class LaticeApplication extends javafx.application.Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		GameBoard gameboard = new GameBoard();
		GameBoardIhm visualGameboard = new GameBoardIhm(gameboard);
		
		root.getChildren().addAll(visualGameboard);
		Scene scene = new Scene(root,1000,700);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
