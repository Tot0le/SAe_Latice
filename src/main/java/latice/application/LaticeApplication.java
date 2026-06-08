package latice.application;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import latice.gui.view.MainMenu;
import latice.util.ImagePath;

public class LaticeApplication extends javafx.application.Application {

	public static void main(String[] args) {
		Application.launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new MainMenu(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Game starting window");
		
		// 
		String iconPath = ImagePath.BACKGROUND_SUN.imagePath();
		Image icon = new Image(getClass().getResourceAsStream(iconPath));
		primaryStage.getIcons().add(icon);
		
		// Ask OS which screen you are using right now
		Screen primaryScreen = Screen.getPrimary();
		
		// get the part of the screen really usable (excluding taskbar for example)
        Rectangle2D visualBounds = primaryScreen.getVisualBounds();
        
        // apply the data to our primaryStage
        primaryStage.setX(visualBounds.getMinX());
        primaryStage.setY(visualBounds.getMinY());
        primaryStage.setWidth(visualBounds.getWidth());
        primaryStage.setHeight(visualBounds.getHeight());
        
		primaryStage.setResizable(true);
		primaryStage.show();

	}
}