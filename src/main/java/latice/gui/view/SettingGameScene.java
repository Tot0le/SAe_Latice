package latice.gui.view;

import java.util.ArrayList;
import latice.util.SetImageView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import latice.gui.controller.MenuBtnController;
import latice.util.ImageLoader;
import latice.util.ImagePath;

public class SettingGameScene extends Scene {
	private BorderPane root;
	private Button launchBtn;
	private Label lblTypePlayer1;
	private Label lblTypePlayer2;
	private TextField txtfUsername1;
	private TextField txtfUsername2;
	private Label lblErrorTypePlayer1;
	private Label lblErrorTypePlayer2;
	
	public SettingGameScene(MenuBtnController btnController) {
		super(new BorderPane(), 1920, 1080);
		
		this.root = (BorderPane) this.getRoot();
		
		Image imageLaunch = ImageLoader.loadImageSafe(ImagePath.LAUNCH_BUTTON.imagePath(),300, 200);
		
		lblTypePlayer1 = new Label("First player username :");
		lblTypePlayer2 = new Label("Second player username :");
		txtfUsername1 = new TextField();
		txtfUsername2 = new TextField();
		lblErrorTypePlayer1 = new Label("");
		lblErrorTypePlayer2 = new Label("");
		lblErrorTypePlayer1.setTextFill(Color.RED);
		lblErrorTypePlayer2.setTextFill(Color.RED);
		lblErrorTypePlayer1.setFont(Font.font("", FontWeight.BOLD, 12));
		lblErrorTypePlayer2.setFont(Font.font("", FontWeight.BOLD, 12));
		launchBtn = new Button("LAUNCH");
		
		launchBtn.setPrefSize(300, 200);
		launchBtn.setMinSize(300, 200);
		launchBtn.setMaxSize(300, 200);
		launchBtn = SetImageView.getImageInButtonBigger(launchBtn,imageLaunch);
		
		VBox middleVBox = new VBox();
		
		middleVBox.getChildren().addAll(lblTypePlayer1, txtfUsername1, lblErrorTypePlayer1, lblTypePlayer2, txtfUsername2, lblErrorTypePlayer2, launchBtn);
		root.setCenter(middleVBox);
		BorderPane.setAlignment(middleVBox, Pos.CENTER);
		
		middleVBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		
		btnController.setSettingGameScene(this);
		
		launchBtn.setOnAction(event -> {
			btnController.launchBtnHandler();
		});

	}
	
	public ArrayList<String> getUsernames(){
		ArrayList<String> usernames = new ArrayList<>();
		boolean formOk = true;
		String username1 = txtfUsername1.getText();
		String username2 = txtfUsername2.getText();
		if (username1.equals("")) {
			lblErrorTypePlayer1.setText("Please enter the first username.");
			formOk = false;
		} else {
			lblErrorTypePlayer1.setText("");
		}
		if (username2.equals("")) {
			lblErrorTypePlayer2.setText("Please enter the second username.");
			formOk = false;
		} else {
			lblErrorTypePlayer2.setText("");
		}
		
		if (formOk) {
			usernames.add(username1);
			usernames.add(username2);
		}
		return usernames;
		
	}
}
