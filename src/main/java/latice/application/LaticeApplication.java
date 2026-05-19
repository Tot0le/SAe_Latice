package latice.application;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class LaticeApplication extends javafx.application.Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//TODO 
		//code venant d'un TP (exemple)
		StackPane root = new StackPane();
		
		//Labels
		Label play = new Label("PLAY");
		Label quit = new Label("QUIT");
		
		// rectangles 
		Rectangle rectPlay = new Rectangle(150, 100, Color.DARKBLUE);
		Rectangle rectQuit = new Rectangle(150, 100, Color.DARKGREEN);
		rectPlay.setX(50);
		rectPlay.setY(100);
		rectQuit.setX(300);
		rectQuit.setY(200);
		
		
		//image
		Image image = new Image(getClass().getResource("/latice_background.png").toExternalForm());

		System.out.println(image.isError());
		
		BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(500, 500, false, false, true, false));
		
		
		root.getChildren().addAll(play, quit, rectPlay, rectQuit);
		
		Scene scene = new Scene(root,500,500);
		root.setBackground(new Background(background));
		
		// Sélection de la fenetre à afficher 
		primaryStage.setScene(scene);
		primaryStage.setTitle("Game starting window");
		primaryStage.setResizable(false);
		primaryStage.show();
	}	
}
