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
import latice.gui.controller.*;

public class MainMenu extends Scene {
	public MainMenu(Stage primaryStage) {
		super(new BorderPane(), 900, 600);
		BorderPane root = (BorderPane) getRoot();

		// load the image via a safe image load function and its path in ImagePath enum
		Image image_background = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_MENU.imagePath(), 250,250);
		Image imagePlay = ImageLoader.loadImageSafe(ImagePath.PLAY_BUTTON.imagePath(), 250,250);
		Image imageQuit = ImageLoader.loadImageSafe(ImagePath.QUIT_BUTTON.imagePath(), 250,250);
		Image imageRules = ImageLoader.loadImageSafe(ImagePath.RULES_BUTTON.imagePath(),250, 250);
		
		//Buttons creation and image attribution + size min and max
		Button btnPlay = new Button("PLAY");
		btnPlay.setPrefSize(150, 100);
		btnPlay.setMinSize(150, 100);
		btnPlay.setMaxSize(150, 100);
		btnPlay = SetImageView.getImageInButton(btnPlay, imagePlay);
		
		Button btnQuit = new Button("QUIT");
		btnQuit.setPrefSize(150, 100);
		btnQuit.setMinSize(150, 100);
		btnQuit.setMaxSize(150, 100);
		btnQuit = SetImageView.getImageInButton(btnQuit, imageQuit);
		
		Button btnRules = new Button("RULES");
		btnRules.setPrefSize(150, 100);
		btnRules.setMinSize(150, 100);
		btnRules.setMaxSize(150, 100);
		btnRules = SetImageView.getImageInButton(btnRules, imageRules);
		
		// Buttons listeners    we make a new primaryStage to bypass the static restrictions
		BtnController controller = new BtnController(primaryStage);
		btnPlay.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
			controller.playBtnHandler();
		});
	    btnQuit.setOnMouseClicked(controller.quitBtnHandler());
	    btnRules.setOnMouseClicked(controller.rulesBtnHandler());
		
		// select the background parameters and the display
		BackgroundImage background = new BackgroundImage(image_background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));

		// VBox creation
		VBox caseTop = new VBox();
		VBox caseBottom = new VBox();
		VBox caseRight = new VBox();

		//  adding the buttons to the VBOXs
		caseRight.getChildren().addAll(btnRules);
		caseRight.setAlignment(Pos.TOP_RIGHT);
		caseRight.setSpacing(10);
		
		caseTop.getChildren().addAll(btnPlay);
		caseTop.setAlignment(Pos.TOP_CENTER);
		caseTop.setSpacing(10);

		caseBottom.getChildren().addAll(btnQuit);
		caseBottom.setAlignment(Pos.BOTTOM_CENTER);
		caseBottom.setSpacing(10);

		// add the VBoxs to the StackPane
		root.setTop(caseTop);
		root.setBottom(caseBottom);
		root.setRight(caseRight);
		
		// Attribution of the background
		root.setBackground(new Background(background));

		// Position attribution of the VBoxs in the BorderPane
		BorderPane.setMargin(caseTop, new Insets(100, 0, 150, 0));
		BorderPane.setMargin(caseBottom, new Insets(0, 0, 200, 0));
		BorderPane.setMargin(caseRight, new Insets(0,100,0,0));

	}
	
}
