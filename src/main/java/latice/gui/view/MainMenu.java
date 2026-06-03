package latice.gui.view;

import java.net.URL;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import latice.gui.controller.MenuBtnController;
import latice.util.ImageLoader;
import latice.util.ImagePath;
import latice.util.MusicPath;
import latice.util.SetImageView;

public class MainMenu extends Scene {
	public MainMenu(Stage primaryStage) {
		super(new BorderPane(), 1920, 1080);
		BorderPane root = (BorderPane) getRoot();

		// load the image via a safe image load function and its path in ImagePath enum
		Image image_background = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_MENU.imagePath(), 1920,1080);
		Image imagePlay = ImageLoader.loadImageSafe(ImagePath.PLAY_BUTTON.imagePath(), 300,200);
		Image imageQuit = ImageLoader.loadImageSafe(ImagePath.QUIT_BUTTON.imagePath(), 150,100);
		Image imageRules = ImageLoader.loadImageSafe(ImagePath.RULES_BUTTON.imagePath(),150, 100);

		//Buttons creation and image attribution + size min and max
		Button btnPlay = new Button("PLAY");
		btnPlay.setPrefSize(300, 200);
		btnPlay.setMinSize(300, 200);
		btnPlay.setMaxSize(300, 200);
		btnPlay = SetImageView.getImageInButtonBigger(btnPlay, imagePlay);
		
		Button btnQuit = new Button("QUIT");
		btnQuit.setPrefSize(150, 100);
		btnQuit.setMinSize(150, 100);
		btnQuit.setMaxSize(150, 100);
		btnQuit = SetImageView.getImageInButtonByDefault(btnQuit, imageQuit);
		
		Button btnRules = new Button("RULES");
		btnRules.setPrefSize(150, 100);
		btnRules.setMinSize(150, 100);
		btnRules.setMaxSize(150, 100);
		btnRules = SetImageView.getImageInButtonByDefault(btnRules, imageRules);
		
		//music implementation 
		String musicFile = MusicPath.MUSIC_MENU.getPath();
		URL resourceUrl = RulesScene.class.getResource(musicFile);
	    Media sound = new Media(resourceUrl.toExternalForm());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
		
		// Buttons listeners    we make a new primaryStage to bypass the static restrictions
		MenuBtnController controller = new MenuBtnController(primaryStage, mediaPlayer);
		btnPlay.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
			controller.playBtnHandler();
		});
	
	    btnQuit.setOnMouseClicked(event -> controller.quitBtnHandler());

	    btnRules.setOnMouseClicked(event -> controller.rulesBtnHandler());

		// select the background parameters and the display
		BackgroundImage background = new BackgroundImage(image_background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
		// VBox creation
		VBox caseTop = new VBox();
		
		// HBox creation
		HBox hboxBottom = new HBox();
		//  adding the buttons to the VBOXs	
		caseTop.getChildren().addAll(btnPlay);
		caseTop.setAlignment(Pos.TOP_CENTER);
		caseTop.setSpacing(10);

		hboxBottom.getChildren().addAll(btnQuit, btnRules);
		hboxBottom.setAlignment(Pos.BOTTOM_CENTER);
		hboxBottom.setSpacing(50);
		// add the VBoxs to the StackPane
		root.setTop(caseTop);
		root.setBottom(hboxBottom);
		
		// Attribution of the background
		root.setBackground(new Background(background));

		// Position attribution of the VBoxs in the BorderPane
		BorderPane.setMargin(caseTop, new Insets(100, 0, 150, 0));
		BorderPane.setMargin(hboxBottom, new Insets(0, 0, 200, 0));

	}
}
