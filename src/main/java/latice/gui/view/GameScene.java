package latice.gui.view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import latice.gui.controller.GameBtnController;
import latice.gui.controller.GameController;
import latice.gui.controller.LabelController;
import latice.gui.controller.MenuBtnController;
import latice.gui.model.GameBoardIhm;
import latice.model.GameBoard;
import latice.model.Player;
import latice.model.Pool;
import latice.model.Rack;
import latice.model.Referee;
import latice.model.StandartPool;

public class GameScene extends Scene { //TODO add a resign button
	private GameBoard gameboard;
	private GameBoardIhm visualGameboard;
	private MenuBtnController menuBtnController;
	private VBox vBoxBottom;
	private VBox vBoxLeft;
	private HBox hBoxBottom;
	private StackPane root;
	private BorderPane gameLayout;
	private BorderPane overlayLayout;
	private Group rackIhmEmplacement;
	private Button endTurnBtn;
	private Button exchangeAllTilesBtn;
//	private Button exchangeTilesBtn;
	private Button confirmBtn;
	private Button buyANewActionBtn;
	private ArrayList<Label> scoreLabels;
	private ArrayList<String> usernames;
	private Label currentPlayer;
	private Label endMessageLbl;
	private Label currentTurn;
	private Label lblpoolTilesNumber;
	
	public GameScene(ArrayList<String> usernames, MenuBtnController menuBtnController) {
		super(new StackPane(), 1920, 1080);
		
		int turnNumber = 0;
		int poolTilesNumber = 0;
		
		this.currentTurn = new Label("Turn " + turnNumber);
		this.lblpoolTilesNumber = new Label("Number of available tiles in the pool : " + poolTilesNumber);
		this.usernames = usernames;
		this.gameboard = new GameBoard();
		this.visualGameboard = new GameBoardIhm(this.gameboard);
		this.menuBtnController = menuBtnController;
		
		this.vBoxBottom = new VBox();
		this.hBoxBottom = new HBox();
		this.rackIhmEmplacement = new Group();
		
		this.root = (StackPane) this.getRoot();
		
		gameLayout = new BorderPane();
		
		overlayLayout = new BorderPane();
		overlayLayout.setVisible(false);
		
		this.root.getChildren().add(gameLayout);
		this.root.getChildren().add(overlayLayout);
		
		BorderPane.setAlignment(visualGameboard, Pos.CENTER);
		VBox vboxTop = new VBox();
		endTurnBtn = new Button("End Turn");
		
		endMessageLbl = new Label("");

		vboxTop.getChildren().add(endMessageLbl);
		
		Label scorePlayer1 = new Label("Score " + usernames.get(0) + " : 0");
		Label scorePlayer2 = new Label("Score " + usernames.get(1) + " : 0");
		
		currentPlayer = new Label();
		VBox vboxRight = new VBox();
		vboxRight.getChildren().addAll(scorePlayer1, scorePlayer2, currentPlayer);
		
		vBoxLeft = new VBox();
		vBoxLeft.getChildren().addAll(currentTurn, lblpoolTilesNumber);
		
		scoreLabels = new ArrayList<>();
		scoreLabels.add(scorePlayer1);
		scoreLabels.add(scorePlayer2);
		
		gameLayout.setRight(vboxRight);
		
		gameLayout.setLeft(vBoxLeft);
		
		gameLayout.setTop(vboxTop);
		
		gameLayout.setCenter(visualGameboard);
		
		exchangeAllTilesBtn = new Button("Exchange All Tiles");
		buyANewActionBtn = new Button("Buy an action.");
		
		buyANewActionBtn.setDisable(true);
		
		hBoxBottom.getChildren().add(exchangeAllTilesBtn);
		hBoxBottom.getChildren().add(buyANewActionBtn);
		hBoxBottom.setAlignment(Pos.CENTER);
		hBoxBottom.setSpacing(100);

		vBoxBottom.getChildren().add(endTurnBtn);
		vBoxBottom.getChildren().add(hBoxBottom);
		vBoxBottom.getChildren().add(rackIhmEmplacement);
		vBoxBottom.setAlignment(Pos.CENTER);
		vBoxBottom.setSpacing(10);
		
		//gridPaneBottom.setAlignment(Pos.TOP_CENTER);
		//gridPaneBottom.setSpacing(10);

		gameLayout.setBottom(vBoxBottom);
		
		BorderPane.setAlignment(vBoxBottom, Pos.BOTTOM_CENTER);
		BorderPane.setAlignment(vboxTop, Pos.TOP_CENTER);
	}
	
	public void launchGame() {
		Pool standartPool = new StandartPool();
		Referee.shuffle(standartPool);
		Pool poolPlayer1 = new Pool();
		Pool poolPlayer2 = new Pool();
		Referee.dealTheCards(standartPool, poolPlayer1, poolPlayer2);
		
		Rack rackPlayer1 = new Rack();
		Rack rackPlayer2 = new Rack();

		Player player1 = new Player(usernames.get(0), 0, rackPlayer1, poolPlayer1, gameboard);
		Player player2 = new Player(usernames.get(1), 0, rackPlayer2, poolPlayer2, gameboard);
		
		player1.drawMaxTile();
		player2.drawMaxTile();
		
		ArrayList<Player> playerList = new ArrayList<>();
		playerList.add(player1);
		playerList.add(player2);
		playerList = (ArrayList<Player>) Referee.randomChoosePlayerOrder(playerList);
		// The game can now start
		
		Referee referee = new Referee(gameboard, playerList);
		LabelController lblController = new LabelController(referee, scoreLabels, currentPlayer, endMessageLbl);
		GameBtnController gameBtnController = new GameBtnController(referee, exchangeAllTilesBtn, buyANewActionBtn);
		GameController gameController = new GameController(this.gameboard, this.visualGameboard, referee, this, lblController, menuBtnController, gameBtnController);
		
		// the end turn button now listen
		endTurnBtn.setOnAction(event -> {
			gameController.endTurnBtnHandler();
			
		});
		
		// the exchangeAllTilesBtn now listen
		exchangeAllTilesBtn.setOnAction(event -> {
			gameController.exchangeAllTilesBtnHandler();
		});
		
		// the exchangeAllTilesBtn now listen
		buyANewActionBtn.setOnAction(event -> {
			gameController.buyANewActionBtnHandler();
		});
	}
	
	public Group rackEmplacement() {
		return this.rackIhmEmplacement;
	}
	
	public BorderPane gameLayout() {
		return gameLayout;
	}

	public void setGameLayout(BorderPane gameLayout) {
		this.gameLayout = gameLayout;
	}

	public BorderPane overlayLayout() {
		return overlayLayout;
	}

	public void setOverlayLayout(BorderPane overlayLayout) {
		this.overlayLayout = overlayLayout;
		this.root.getChildren().remove(overlayLayout);
		this.overlayLayout.setVisible(true);
		this.root.getChildren().add(this.overlayLayout);
	}
}
