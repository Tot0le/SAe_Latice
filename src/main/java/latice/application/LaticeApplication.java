package latice.application;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import latice.gui.model.RackIhm;
import latice.gui.view.GameScene;
import latice.gui.view.MainMenu;

@SuppressWarnings("unused")
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