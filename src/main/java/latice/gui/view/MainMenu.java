package latice.gui.view;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import latice.util.ImageLoader;
import latice.util.ImagePath;

public class MainMenu {
	public static Scene createMainMenuScene() {
		BorderPane root = new BorderPane();

		//Labels
		Label play = new Label("PLAY");
		Label quit = new Label("QUIT");

		// rectangles
		Rectangle rectPlay = new Rectangle(150, 100, Color.RED);
		Rectangle rectQuit = new Rectangle(150, 100, Color.PINK);

		// load the image via a safe image load function and its path in ImagePath enum
		Image image = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_MENU.imagePath(), 250,250);

		// sélectionne les paramètres de background ainsi que l'image affichée
		BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));

		// VBox creation
		VBox caseTop = new VBox();
		VBox caseBottom = new VBox();

		//  buttons creations with image


		caseTop.getChildren().addAll(rectPlay, play);
		caseTop.setAlignment(Pos.TOP_CENTER);
		caseTop.setSpacing(10);

		caseBottom.getChildren().addAll(rectQuit, quit);
		caseBottom.setAlignment(Pos.BOTTOM_CENTER);
		caseBottom.setSpacing(10);

		// add the elements to the StackPane
		root.setTop(caseTop);
		root.setBottom(caseBottom);


		Scene scene = new Scene(root,900,600);
		root.setBackground(new Background(background));

		BorderPane.setMargin(caseTop, new Insets(100, 0, 150, 0));
		BorderPane.setMargin(caseBottom, new Insets(0, 0, 200, 0));


		return scene;

	}
}
