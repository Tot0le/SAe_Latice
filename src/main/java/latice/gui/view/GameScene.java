package latice.gui.view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import latice.gui.controller.GameController;
import latice.gui.model.GameBoardIhm;
import latice.model.Factory;
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
	private Group rackEmplacement;
	private BorderPane root;
	private Button endTurnBtn;
	
	public GameScene() {
		this.gameboard = new GameBoard();
		this.visualGameboard = new GameBoardIhm(this.gameboard);
		this.rackEmplacement = new Group();
	}
	
	public Scene createGameScene() {
		root = new BorderPane();

		BorderPane.setAlignment(visualGameboard, Pos.CENTER);
		Group groupTop = new Group();
		endTurnBtn = new Button("End Turn");
		groupTop.getChildren().add(endTurnBtn); //TODO continue button implementation

		root.setTop(groupTop);
		root.setCenter(visualGameboard);
		root.setBottom(rackEmplacement);
		BorderPane.setAlignment(rackEmplacement, Pos.TOP_CENTER);
		
		
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
		Player player1 = new Player(0, rackPlayer1, poolPlayer1, gameboard);
		Player player2 = new Player(0, rackPlayer2, poolPlayer2, gameboard);
		
		player1.drawMaxTile();
		player2.drawMaxTile();
		
		ArrayList<Player> playerList = new ArrayList<>();
		playerList.add(player1);
		playerList.add(player2);
		playerList = (ArrayList<Player>) Referee.randomChoosePlayerOrder(playerList);
		System.out.println(playerList);
		// The game can now start
		
		GameController gameController = new GameController(this.gameboard, this.visualGameboard, playerList, this);
		gameController.displayCurrentPlayerRack();
		gameController.getCurrentPlayerRackIhm().updateRackTiles();
		
		// the end turn button now listen
		endTurnBtn.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
			gameController.endTurnBtnHandler();
			
		});
		
	}
	
	public Group rackEmplacement() {
		return this.rackEmplacement;
	}
	
//	public void bindButtonController(GameController gameController) {
//		this
//	}
}
