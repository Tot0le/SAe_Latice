package latice.gui.view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import latice.gui.controller.GameBtnController;
import latice.gui.controller.GameController;
import latice.gui.controller.LabelController;
import latice.gui.model.GameBoardIhm;
import latice.model.GameBoard;
import latice.model.Player;
import latice.model.Pool;
import latice.model.Rack;
import latice.model.Referee;
import latice.model.StandartPool;
import latice.model.tile.Color;
import latice.model.tile.Shape;
import latice.model.tile.Tile;

public class GameScene extends Scene {
	private GameBoard gameboard;
	private GameBoardIhm visualGameboard;
	private HBox hboxBottom;
	private BorderPane root;
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
	
	public GameScene(ArrayList<String> usernames) {
		super(new BorderPane(), 1920, 1080);
		this.usernames = usernames;
		this.gameboard = new GameBoard();
		this.visualGameboard = new GameBoardIhm(this.gameboard);
		this.hboxBottom = new HBox();
		this.rackIhmEmplacement = new Group();
		
		this.root = (BorderPane) this.getRoot();
		
		BorderPane.setAlignment(visualGameboard, Pos.CENTER);
		VBox vboxTop = new VBox();
		endTurnBtn = new Button("End Turn");
		
		endMessageLbl = new Label("");

		vboxTop.getChildren().addAll(endTurnBtn, endMessageLbl);
		
		Label scorePlayer1 = new Label("Score player 1 : 0");
		Label scorePlayer2 = new Label("Score player 2 : 0");
		
		currentPlayer = new Label();
		VBox vboxRight = new VBox();
		vboxRight.getChildren().addAll(scorePlayer1, scorePlayer2, currentPlayer);
		
		scoreLabels = new ArrayList<>();
		scoreLabels.add(scorePlayer1);
		scoreLabels.add(scorePlayer2);
		
		root.setRight(vboxRight);
		
		root.setTop(vboxTop);
		
		root.setCenter(visualGameboard);
		
		hboxBottom.getChildren().add(rackIhmEmplacement);
		exchangeAllTilesBtn = new Button("Exchange All Tiles");
		hboxBottom.getChildren().add(exchangeAllTilesBtn);
		
		buyANewActionBtn = new Button("Buy an action.");
		buyANewActionBtn.setDisable(true);
		hboxBottom.getChildren().add(buyANewActionBtn);

		root.setBottom(hboxBottom);
		BorderPane.setAlignment(hboxBottom, Pos.BOTTOM_CENTER);
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
		rackPlayer1.addTile(new Tile(Color.Green, Shape.Bird));
		rackPlayer2.addTile(new Tile(Color.Magenta, Shape.Bird));
		Player player1 = new Player(usernames.get(0), 1, rackPlayer1, poolPlayer1, gameboard);
		Player player2 = new Player(usernames.get(1), 1, rackPlayer2, poolPlayer2, gameboard);
		
		player1.drawMaxTile();
		player2.drawMaxTile();
		
		ArrayList<Player> playerList = new ArrayList<>();
		playerList.add(player1);
		playerList.add(player2);
		playerList = (ArrayList<Player>) Referee.randomChoosePlayerOrder(playerList);
		// The game can now start
		
		LabelController lblController = new LabelController(scoreLabels, currentPlayer, endMessageLbl);
		GameBtnController gameBtnController = new GameBtnController(buyANewActionBtn);
		Referee referee = new Referee(playerList);
		GameController gameController = new GameController(this.gameboard, this.visualGameboard, referee, this, lblController, gameBtnController);
		
		// the end turn button now listen
		endTurnBtn.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
			gameController.endTurnBtnHandler();
			
		});
		
		// the exchangeAllTilesBtn now listen
		exchangeAllTilesBtn.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
			gameController.exchangeAllTilesBtnHandler();
			
		});
		
		// the exchangeAllTilesBtn now listen
		buyANewActionBtn.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
			gameController.buyANewActionBtnHandler();
		});
	}
	
	public Group rackEmplacement() {
		return this.rackIhmEmplacement;
	}
	
//	public void bindButtonController(GameController gameController) {
//		this
//	}
}
