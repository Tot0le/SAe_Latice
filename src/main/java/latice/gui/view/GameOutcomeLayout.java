package latice.gui.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import latice.gui.CssStyle;
import latice.gui.controller.MenuBtnController;
import latice.model.Player;

public class GameOutcomeLayout extends BorderPane {
	private Label resultLbl;
	private Label winnerLbl;
	private Button closeLayoutBtn;
	private Button mainMenuBtn;
	private Button replaySameConfig; // TODO in GameScene, when no action has been done, a button to cancel the game
	
	public GameOutcomeLayout(Player winner, MenuBtnController menuBtnController, BorderPane gameLayout) {
		gameLayout.setDisable(true);
		resultLbl = new Label();
		winnerLbl = new Label();
		
		if (winner == null) {
			this.resultLbl.setTextFill(Color.WHITE);
			this.resultLbl.setFont(Font.font("", FontWeight.BOLD, 26));
			this.resultLbl.setText("It's a draw !");
		} else {
			this.resultLbl.setTextFill(Color.GOLD);
			this.resultLbl.setFont(Font.font("", FontWeight.BOLD, 26));
			this.resultLbl.setText("Congratulations !");
			
			this.winnerLbl.setTextFill(Color.GOLD);
			this.winnerLbl.setFont(Font.font("", FontWeight.BOLD, 20));
			this.winnerLbl.setText(winner.username() + " wins !");
		}
		
		closeLayoutBtn = new Button("×");
		
		closeLayoutBtn.setOnAction(event -> {
		    this.setVisible(false);
		    gameLayout.setDisable(false);
		});
		
		mainMenuBtn = new Button("Main menu");
		
		mainMenuBtn.setOnAction(event -> {
			menuBtnController.backBtnHandler();
		});
		
		replaySameConfig = new Button("Replay");
		

		
		replaySameConfig.setOnAction(event -> {
			GameScene gameScene = new GameScene(menuBtnController.usernames(), menuBtnController);
    		
    		menuBtnController.primaryStage().setScene(gameScene);
    		gameScene.launchGame();
		});

		VBox middleVbox = new VBox();
		VBox topTopVbox = new VBox();
		AnchorPane topAnchorPane = new AnchorPane();
		HBox bottomMiddleHbox = new HBox();

		middleVbox.setPadding(new javafx.geometry.Insets(10, 20, 20, 20));
		middleVbox.setSpacing(25);
		middleVbox.setAlignment(Pos.TOP_CENTER);
		middleVbox.setMaxSize(400, 300);
		
		topTopVbox.setPadding(new javafx.geometry.Insets(10, 10, 25, 10));
        topTopVbox.setSpacing(10);
        topTopVbox.setAlignment(Pos.CENTER);

        AnchorPane.setTopAnchor(closeLayoutBtn, 0.0);
        AnchorPane.setRightAnchor(closeLayoutBtn, 0.0);
        
        resultLbl.setMaxWidth(Double.MAX_VALUE);
        resultLbl.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(resultLbl, 5.0);
        AnchorPane.setLeftAnchor(resultLbl, 0.0);
        AnchorPane.setRightAnchor(resultLbl, 0.0);

        topAnchorPane.getChildren().addAll(resultLbl, closeLayoutBtn);
        topTopVbox.getChildren().addAll(topAnchorPane, winnerLbl);
        
        bottomMiddleHbox.setAlignment(Pos.CENTER);
        
        mainMenuBtn.setPrefWidth(150);
        replaySameConfig.setPrefWidth(150);
        
        HBox.setMargin(mainMenuBtn, new javafx.geometry.Insets(0, 15, 0, 0));
        HBox.setMargin(replaySameConfig, new javafx.geometry.Insets(0, 0, 0, 15));
        bottomMiddleHbox.getChildren().addAll(mainMenuBtn, replaySameConfig);
        
        VBox.setMargin(bottomMiddleHbox, new javafx.geometry.Insets(30, 0, 0, 0));
        
        middleVbox.getChildren().addAll(topTopVbox, bottomMiddleHbox);
        
        CssStyle.resultPageCss(closeLayoutBtn, mainMenuBtn, replaySameConfig, middleVbox, topTopVbox);

		this.setCenter(middleVbox);
		
	}


	public Label resultLbl() {
		return resultLbl;
	}


	public void setResultLbl(Label resultLbl) {
		this.resultLbl = resultLbl;
	}
}
