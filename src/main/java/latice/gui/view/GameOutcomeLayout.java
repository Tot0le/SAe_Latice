package latice.gui.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import latice.gui.controller.MenuBtnController;
import latice.model.Player;

public class GameOutcomeLayout extends BorderPane {
	private Label resultLbl;
	private Button closeLayoutBtn;
	private Button mainMenuBtn;
	private Button replaySameConfig; // TODO in GameScene, when no action has been done, a button to cancel the game
	private Rectangle background;
	
	public GameOutcomeLayout(Player winner, MenuBtnController menuBtnController, BorderPane gameLayout) {
		gameLayout.setDisable(true);
		resultLbl = new Label();
		
		if (winner == null) {
			this.resultLbl.setTextFill(Color.GRAY);
			this.resultLbl.setFont(Font.font("", FontWeight.BOLD, 20));
			this.resultLbl.setText("It's a draw !");
		} else {
			this.resultLbl.setTextFill(Color.GOLD);
			this.resultLbl.setFont(Font.font("", FontWeight.BOLD, 20));
			this.resultLbl.setText("Congratulations ! " + winner.username() + " wins !");
		}
		
		this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");
		
		closeLayoutBtn = new Button("×");
		
		closeLayoutBtn.setOnAction(event -> {
		    this.setVisible(false);
		    gameLayout.setDisable(false);
		});
		
		mainMenuBtn = new Button("Main menu");
		
		mainMenuBtn.setOnAction(event -> {
			MainMenu mainMenu = new MainMenu(menuBtnController.primaryStage());
    		
    		menuBtnController.primaryStage().setScene(mainMenu);
		});
		
		
		replaySameConfig = new Button("Replay");
		
		replaySameConfig.setOnAction(event -> {
			GameScene gameScene = new GameScene(menuBtnController.usernames(), menuBtnController);
    		
    		menuBtnController.primaryStage().setScene(gameScene);
    		gameScene.launchGame();
		});

		VBox middleVbox = new VBox();
		HBox topMiddleHbox = new HBox();
		middleVbox.setStyle("-fx-background-color: #262421;");
		topMiddleHbox.setStyle("-fx-background-color: #3C3A38;");
		topMiddleHbox.getChildren().add(resultLbl);
		topMiddleHbox.getChildren().add(closeLayoutBtn);
		
		middleVbox.getChildren().add(topMiddleHbox);
		HBox bottomMiddleHbox = new HBox();
		bottomMiddleHbox.getChildren().addAll(mainMenuBtn, replaySameConfig);
		
		middleVbox.getChildren().add(bottomMiddleHbox);
		
		middleVbox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		this.setCenter(middleVbox);
		
	}


	public Label resultLbl() {
		return resultLbl;
	}


	public void setResultLbl(Label resultLbl) {
		this.resultLbl = resultLbl;
	}
}
