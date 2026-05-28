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
import latice.gui.controller.GameController;
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

public class GameScene {
	private GameBoard gameboard;
	private GameBoardIhm visualGameboard;
	private HBox hboxBottom;
	private BorderPane root;
	private Group rackIhmEmplacement;
	private Button endTurnBtn;
	private Button exchangeAllTilesBtn;
//	private Button exchangeTilesBtn;
	private Button confirmBtn;
	private ArrayList<Label> scoreLabels;
	private ArrayList<String> usernames;
	
	public GameScene(ArrayList<String> usernames) {
		this.usernames = usernames;
		this.gameboard = new GameBoard();
		this.visualGameboard = new GameBoardIhm(this.gameboard);
		this.hboxBottom = new HBox();
		this.rackIhmEmplacement = new Group();
	}
	
	public Scene createGameScene() {
		root = new BorderPane();

		BorderPane.setAlignment(visualGameboard, Pos.CENTER);
		Group groupTop = new Group();
		endTurnBtn = new Button("End Turn");

		groupTop.getChildren().add(endTurnBtn);
		
		Label scorePlayer1 = new Label("Score player 1 : 0");
		Label scorePlayer2 = new Label("Score player 2 : 0");
		VBox vboxRight = new VBox();
		vboxRight.getChildren().addAll(scorePlayer1, scorePlayer2);
		
		scoreLabels = new ArrayList<>();
		scoreLabels.add(scorePlayer1);
		scoreLabels.add(scorePlayer2);
		
		root.setRight(vboxRight);
		
		root.setTop(groupTop);
		root.setCenter(visualGameboard);
		
		hboxBottom.getChildren().add(rackIhmEmplacement);
		exchangeAllTilesBtn = new Button("Exchange All Tiles");
		hboxBottom.getChildren().add(exchangeAllTilesBtn);
		///// V8 FEATURE DO NOT DELETE :
//		exchangeTilesBtn = new Button("Exchange Tiles");
//		hboxBottom.getChildren().add(exchangeTilesBtn);
//		
//		confirmBtn = new Button("Confirm");
//		hboxBottom.getChildren().add(confirmBtn);
		///// V8 FEATURE DO NOT DELETE

		root.setBottom(hboxBottom);
		BorderPane.setAlignment(hboxBottom, Pos.BOTTOM_CENTER);
		
		
		Scene scene = new Scene(root,1920,1080);
		// note that the size of the window does not change if the scene change their size during the program execution

		return scene;
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
		Player player1 = new Player(usernames.get(0), 0, rackPlayer1, poolPlayer1, gameboard);
		Player player2 = new Player(usernames.get(1), 0, rackPlayer2, poolPlayer2, gameboard);
		
		player1.drawMaxTile();
		player2.drawMaxTile();
		
		ArrayList<Player> playerList = new ArrayList<>();
		playerList.add(player1);
		playerList.add(player2);
		playerList = (ArrayList<Player>) Referee.randomChoosePlayerOrder(playerList);
		System.out.println(playerList);
		// The game can now start
		
		GameController gameController = new GameController(this.gameboard, this.visualGameboard, playerList, this, scoreLabels);
		gameController.displayCurrentPlayerRack();
		gameController.getCurrentPlayerRackIhm().updateRackTiles();
		
		// the end turn button now listen
		endTurnBtn.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
			gameController.endTurnBtnHandler();
			
		});
		
		// the exchangeAllTilesBtn now listen
		exchangeAllTilesBtn.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
			gameController.exchangeAllTilesBtnHandler();
			
		});
		///// V8 FEATURE DO NOT DELETE :
//		// the exchangeTilesBtn now listen
//		exchangeTilesBtn.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
//			gameController.exchangeTilesBtnHandler();
//			
//		});
//		
//		// the exchangeTilesBtn now listen
//		confirmBtn.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
//			gameController.confirmBtnHandler();
//			
//		});
		///// V8 FEATURE DO NOT DELETE
	}
	
	public Group rackEmplacement() {
		return this.rackIhmEmplacement;
	}
	
//	public void bindButtonController(GameController gameController) {
//		this
//	}
}
