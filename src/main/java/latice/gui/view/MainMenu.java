package latice.gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import latice.util.ImageLoader;
import latice.util.ImagePath;
import latice.util.SetImageView;
import latice.gui.view.GameScene;

public class MainMenu {
	public static Scene createMainMenuScene() {
		BorderPane root = new BorderPane();

		// load the image via a safe image load function and its path in ImagePath enum
		Image image_background = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_MENU.imagePath(), 250,250);
		Image image_play = ImageLoader.loadImageSafe(ImagePath.PLAY_BUTTON.imagePath(), 250,250);
		Image image_quit = ImageLoader.loadImageSafe(ImagePath.QUIT_BUTTON.imagePath(), 250,250);
		//Buttons creation, image attribution and size
		Button btnPlay = new Button("PLAY");
		btnPlay.setPrefSize(150, 100);
		btnPlay = SetImageView.getImageInButton(btnPlay, image_play);
		final Button btnFinalPlay = btnPlay;
		
		Button btnQuit = new Button("QUIT"); 
		btnQuit.setPrefSize(150, 100);
		btnQuit = SetImageView.getImageInButton(btnQuit, image_quit);
		final Button btnFinalQuit = btnQuit;
		
		// sélectionne les paramètres de background ainsi que l'image affichée
		BackgroundImage background = new BackgroundImage(image_background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));

		// VBox creation
		VBox caseTop = new VBox();
		VBox caseBottom = new VBox();

		//  buttons creations with image


		caseTop.getChildren().addAll(btnPlay);
		caseTop.setAlignment(Pos.TOP_CENTER);
		caseTop.setSpacing(10);

		caseBottom.getChildren().addAll(btnQuit);
		caseBottom.setAlignment(Pos.BOTTOM_CENTER);
		caseBottom.setSpacing(10);

		// add the elements to the StackPane
		root.setTop(caseTop);
		root.setBottom(caseBottom);


		Scene scene = new Scene(root,900,600);
		root.setBackground(new Background(background));

		BorderPane.setMargin(caseTop, new Insets(100, 0, 150, 0));
		BorderPane.setMargin(caseBottom, new Insets(0, 0, 200, 0));
		
		btnPlay.setOnAction(e -> goToGameScene(btnFinalPlay));

		return scene;

	}
	public static void goToGameScene(Button bouton) {
		GameScene gameScene = new GameScene();
		Scene scene = gameScene.createGameScene();
		
		gameScene.launchGame();
		
	}
	
}
