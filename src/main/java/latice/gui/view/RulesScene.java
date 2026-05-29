package latice.gui.view;

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
import latice.util.SetImageView;

public class RulesScene extends Scene {
	
	public RulesScene(Stage primaryStage) {
		super(new BorderPane(), 1920, 1080);
		
		BorderPane root = (BorderPane) this.getRoot();
		Image imageRulesBackground = ImageLoader.loadImageSafe(ImagePath.RULES_BACKGROUND.imagePath(), 250,250);
		Image imageQuit = ImageLoader.loadImageSafe(ImagePath.QUIT_BUTTON.imagePath(), 250,250);
		
		Button btnQuitRules = new Button("QUIT"); 
		btnQuitRules.setPrefSize(150, 100);
		btnQuitRules.setMinSize(150, 100);
		btnQuitRules.setMaxSize(150, 100);
		btnQuitRules = SetImageView.getImageInButton(btnQuitRules, imageQuit);
		
		MenuBtnController controller = new MenuBtnController(primaryStage);
	    btnQuitRules.setOnMouseClicked(controller.quitBtnHandler());
		
		BackgroundImage rulesBackground = new BackgroundImage(imageRulesBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
		root.setBackground(new Background(rulesBackground));
		
		//VBox 
		VBox caseRight = new VBox();
		
		caseRight.getChildren().addAll(btnQuitRules);
		caseRight.setAlignment(Pos.TOP_RIGHT);
		caseRight.setSpacing(10);
		
		root.setRight(caseRight);
		
		BorderPane.setMargin(caseRight, new Insets(0,100,100,0));
		
	}
}
