package latice.gui.view;

import javax.print.attribute.standard.Media;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import latice.gui.controller.MenuBtnController;
import latice.util.ImageLoader;
import latice.util.ImagePath;
import latice.util.MusicPath;
import latice.util.SetImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class RulesScene extends Scene {
	
	public RulesScene(Stage primaryStage) {
		super(new BorderPane(), 1920, 1080);
		
		BorderPane root = (BorderPane) this.getRoot();
		
		//images call from ImagePath
		Image imageRulesBackground = ImageLoader.loadImageSafe(ImagePath.RULES_BACKGROUND.imagePath(), 1920,1080);
		Image imageQuit = ImageLoader.loadImageSafe(ImagePath.QUIT_BUTTON.imagePath(), 150,100);
		Image imageBack = ImageLoader.loadImageSafe(ImagePath.BACK_BUTTON.imagePath(), 150,100);
		
		//Music call from MusicPath

		String musicFile = MusicPath.MUSIC_SETTINGS.getPath();  

		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();

		
		// Buttons creation with the listeners
		Button btnQuitRules = new Button("QUIT"); 
		btnQuitRules.setPrefSize(150, 100);
		btnQuitRules.setMinSize(150, 100);
		btnQuitRules.setMaxSize(150, 100);
		btnQuitRules = SetImageView.getImageInButtonByDefault(btnQuitRules, imageQuit);
		
		Button btnBackRules = new Button("BACK");
		btnBackRules.setPrefSize(150, 100);
		btnBackRules.setMinSize(150, 100);
		btnBackRules.setMaxSize(150, 100);
		btnBackRules = SetImageView.getImageInButtonByDefault(btnBackRules, imageBack);
		
		MenuBtnController controller = new MenuBtnController(primaryStage);
	    btnQuitRules.setOnMouseClicked(controller.quitBtnHandler());
	    btnBackRules.setOnMouseClicked(event -> controller.BackBtnHandler());
		
	    //Background instanciation
		BackgroundImage rulesBackground = new BackgroundImage(imageRulesBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
		root.setBackground(new Background(rulesBackground));

		//VBox creation and implementation
		VBox caseRight = new VBox();
		VBox caseLeft = new VBox();

		caseLeft.getChildren().addAll(btnBackRules);
		caseLeft.setAlignment(Pos.TOP_LEFT);
		caseRight.setSpacing(10);
		
		caseRight.getChildren().addAll(btnQuitRules);
		caseRight.setAlignment(Pos.TOP_RIGHT);
		caseRight.setSpacing(10);
		
		root.setRight(caseRight);
		root.setLeft(caseLeft);
		
		BorderPane.setMargin(caseRight, new Insets(100,100,100,0));
		BorderPane.setMargin(caseLeft, new Insets(100,0,100,100));
		
	}
}
