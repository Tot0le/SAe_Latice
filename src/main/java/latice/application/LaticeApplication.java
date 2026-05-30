package latice.application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import latice.gui.view.MainMenu;

public class LaticeApplication extends javafx.application.Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new MainMenu(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Game starting window");
		primaryStage.setResizable(true);
		primaryStage.setFullScreen(true);
		primaryStage.show();

	}
}