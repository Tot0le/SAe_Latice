package latice.application;

import java.util.ArrayList;

import latice.gui.Console;
import latice.model.Factory;
import latice.model.GameBoard;
import latice.model.Player;
import latice.model.Pool;
import latice.model.Position;
import latice.model.Rack;
import latice.model.Referee;
import latice.model.tile.Tile;

public class LaticeApplicationConsole extends Referee{
	private final Integer nbTileMax = 72 ;
	public static void main(String[] args) {
		
		// real game preparation :
		
		ArrayList<Tile> tiles = Factory.createAllTiles();
		Pool bigPool = new Pool(tiles);
		Pool poolPlayer1 = new Pool();
		Pool poolPlayer2 = new Pool();
		Referee.shuffle(bigPool);
		Referee.dealTheCards(bigPool, poolPlayer1, poolPlayer2); 

		Rack rackPlayer1 = new Rack();
		Rack rackPlayer2 = new Rack();
		
		Player player1 = new Player(0, rackPlayer1, poolPlayer1);
		Player player2 = new Player(0, rackPlayer2, poolPlayer2);
		
		for (int i = 0; i < 5; i++) {
			player1.drawATile();
			player2.drawATile();
	}
		player1.drawATile();

		GameBoard gameboard = new GameBoard(10, 9);
		Console.message(gameboard.toAscii());
		
		gameboard.put(new Position(1,1), rackPlayer1.popTile(0));
		Console.message(gameboard.toAscii());
		gameboard.put(new Position(1,2), rackPlayer1.popTile(0));
		Console.message(gameboard.toAscii());
		Console.message(rackPlayer1.tilesAmounts() + "");
	}	
}
