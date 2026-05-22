package latice.gui.view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import latice.gui.controller.GameController;
import latice.gui.model.GameBoardIhm;
import latice.gui.model.RackIhm;
import latice.model.Factory;
import latice.model.GameBoard;
import latice.model.Player;
import latice.model.Pool;
import latice.model.Position;
import latice.model.Rack;
import latice.model.Referee;
import latice.model.tile.Color;
import latice.model.tile.Shape;
import latice.model.tile.Tile;

public class GameScene {
	private GameBoard gameboard;
	private GameBoardIhm visualGameboard;
	
	public GameScene() {
		this.gameboard = new GameBoard();
		this.visualGameboard = new GameBoardIhm(this.gameboard);
	}
	
	public Scene createGameScene() {
		BorderPane root = new BorderPane();
		
		//Test RackIhm
		Rack rack1 = new Rack();
		ArrayList<Tile> tiles = new ArrayList<>();
		
		Tile tile1 = new Tile(Color.Green, Shape.Bird);
		Tile tile2 = new Tile(Color.Navy, Shape.Gecko);
		Tile tile3 = new Tile(Color.Red, Shape.Feather);
		
		tiles.add(tile1);
		tiles.add(tile2);
		tiles.add(tile3);
		
		rack1.setTiles(tiles);
		
		//Test Rack
		System.out.println(rack1.getTiles());
		RackIhm visualRack = new RackIhm(rack1);
		visualRack.updateRackTiles();
		
		//GAMEBOARD
//		gameboard.put(new Position(7,7), new Tile(Color.Green, Shape.Bird)); // testing if the tiles appears
//		visualGameboard.update();
//		gameboard.removeTileAt(new Position(7,7));
//		visualGameboard.update();

		BorderPane.setAlignment(visualGameboard, Pos.CENTER);
		
		//RACK
		root.setCenter(visualGameboard);
		root.setBottom(visualRack);
		BorderPane.setAlignment(visualRack, Pos.TOP_CENTER);
		
		
		Scene scene = new Scene(root,1920,1080);
		// note that the size of the window does not change if the scene change their size during the program execution

		return scene;
	}
	
	public void launchGame() {
		ArrayList<Tile> tiles = Factory.createAllTiles();
		Pool bigPool = new Pool(tiles); // TODO watch into the standartDeck TP
		Referee.shuffle(bigPool);
		Pool poolPlayer1 = new Pool();
		Pool poolPlayer2 = new Pool();
		Referee.dealTheCards(bigPool, poolPlayer1, poolPlayer2);
		
		Rack rackPlayer1 = new Rack();
		Rack rackPlayer2 = new Rack();
		
		Player player1 = new Player(0, rackPlayer1, poolPlayer1, gameboard);
		Player player2 = new Player(0, rackPlayer2, poolPlayer2, gameboard);
		
		player1.drawMaxTile();
		player2.drawMaxTile();
		
		ArrayList<Player> playerList = new ArrayList<>();
		
		playerList = (ArrayList<Player>) Referee.randomChoosePlayerOrder(playerList);
		
		// The game can now start
		
		GameController gameController = new GameController(this.gameboard, this.visualGameboard, playerList);
//		gameController.nextTurn();
		
		
		
	}
}
