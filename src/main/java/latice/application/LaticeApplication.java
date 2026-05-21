package latice.application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
		//TODO 
		//code venant d'un TP (exemple)
		BorderPane root = new BorderPane();
		
		//Labels
		Label play = new Label("PLAY");
		Label quit = new Label("QUIT");
		
		// rectangles 
		Rectangle rectPlay = new Rectangle(150, 100, Color.RED);
		Rectangle rectQuit = new Rectangle(150, 100, Color.PINK);
		
		// Va chercher l'image dans la dossier ressources
		Image image = new Image(getClass().getResource("/images/latice_background.png").toExternalForm());

		// sélectionne les paramètres de background ainsi que l'image affichée
		BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
		
		// VBox création
		VBox caseTop = new VBox();
		VBox caseBottom = new VBox();
		
		caseTop.getChildren().addAll(rectPlay, play);
		caseTop.setAlignment(Pos.TOP_CENTER);
		caseTop.setSpacing(10);
		
		caseBottom.getChildren().addAll(rectQuit, quit);
		caseBottom.setAlignment(Pos.BOTTOM_CENTER);
		caseBottom.setSpacing(10);
		
		// Ajout des éléments au StackPane
		root.setTop(caseTop);
		root.setBottom(caseBottom);
		
		
		Scene scene = new Scene(root,900,600);
		root.setBackground(new Background(background));
		
		BorderPane.setMargin(caseTop, new Insets(100, 0, 150, 0));
		BorderPane.setMargin(caseBottom, new Insets(0, 0, 200, 0));
		
		// Sélection de la fenetre à afficher 
		primaryStage.setScene(scene);
		primaryStage.setTitle("Game starting window");
		primaryStage.setResizable(false);
		GameBoard gameboard = new GameBoard();
//		GameBoardIhm visualGameboard = new GameBoardIhm(gameboard);
//		
//		root.getChildren().addAll(visualGameboard);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}	
}