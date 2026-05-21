package latice.application;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import latice.gui.view.GameScene;

public class LaticeApplication extends javafx.application.Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = GameScene.createGameScene();
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
