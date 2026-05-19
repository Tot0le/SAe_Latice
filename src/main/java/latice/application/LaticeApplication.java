package latice.application;


import javafx.application.Application;
import javafx.scene.Scene;
<<<<<<< HEAD
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
=======
import javafx.scene.layout.VBox;
>>>>>>> 59a1ba56dd1c4b63da1c5f54dbb48f9a502bb502
import javafx.stage.Stage;
import latice.gui.model.GameBoardIhm;
import latice.model.GameBoard;

@SuppressWarnings("unused")
public class LaticeApplication extends javafx.application.Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
<<<<<<< HEAD
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
=======
		VBox root = new VBox();
		GameBoard gameboard = new GameBoard();
//		GameBoardIhm visualGameboard = new GameBoardIhm(gameboard);
//		
//		root.getChildren().addAll(visualGameboard);
		Scene scene = new Scene(root,1000,700);
		
		primaryStage.setScene(scene);
>>>>>>> 59a1ba56dd1c4b63da1c5f54dbb48f9a502bb502
		primaryStage.show();
	}	
}
