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
//		Console.title("-- Bienvenue dans notre magnifique jeu de latice ! -- ");
//		Scanner scan = new Scanner(System.in);
//		Console.message("Veuillez saisir le nombre de joueurs :");
//		int nbJoueurs = scan.nextInt();
//		Console.message("Il y a " + nbJoueurs+" joueurs");
//		
//		Tile tiletest = new Tile(Color.Green, Shape.Bird);
//		Console.message(tiletest);
		
		// real game preparation :
		
		ArrayList<Tile> tiles = Factory.createAllTiles();
		Pool bigPool = new Pool(tiles);
		Pool poolPlayer1 = new Pool();
		Pool poolPlayer2 = new Pool();
		Referee.shuffle(bigPool);
		Pool[] pools = Referee.dealTheCards(bigPool); 
		//TODO refacto the dealsTheCards : dealTheCards(bigPool, poolPlayer1, poolPlayer2) : void
		
		poolPlayer1.setTiles(pools[0].getTiles());
		poolPlayer2.setTiles(pools[1].getTiles());
//		Console.message(poolPlayer1);
//		Console.message(poolPlayer2);
//		Console.message(poolPlayer1.tilesAmounts());
//		Console.message(poolPlayer2.tilesAmounts());

		Rack rackPlayer1 = new Rack();
		Rack rackPlayer2 = new Rack();
		
		Player player1 = new Player(0, rackPlayer1, poolPlayer1);
		Player player2 = new Player(0, rackPlayer2, poolPlayer2);
		
		for (int i = 0; i < 5; i++) {
			Console.message("\n\n\n ---- " + i + "eme iteration ----" );
			Console.message("Rack player 1 : " + rackPlayer1);
			Console.message("Pool player 1 : \n" + poolPlayer1 + "\nTiles Amount : " + poolPlayer1.tilesAmounts());
			player1.drawATile();
			Console.message("Rack player 2 : " + rackPlayer2);
			Console.message("Pool player 2 : \n" + poolPlayer2 + "\nTiles Amount : " + poolPlayer2.tilesAmounts());
			player2.drawATile();
	}
		Console.message("\n\n\n ---- 5eme iteration ----" );
		Console.message("Rack player 1 : " + rackPlayer1);
		Console.message("Pool player 1 : \n" + poolPlayer1 + "\nTiles Amount : " + poolPlayer1.tilesAmounts());
		Console.message("Rack player 2 : " + rackPlayer2);
		Console.message("Pool player 2 : \n" + poolPlayer2 + "\nTiles Amount : " + poolPlayer2.tilesAmounts());
		
		player1.drawATile();
		Console.message("\n\n\n ---- 6eme iteration ----" );
		Console.message("Rack player 1 : " + rackPlayer1);
		Console.message("Pool player 1 : \n" + poolPlayer1 + "\nTiles Amount : " + poolPlayer1.tilesAmounts());
		Console.message("Rack player 2 : " + rackPlayer2);
		Console.message("Pool player 2 : \n" + poolPlayer2 + "\nTiles Amount : " + poolPlayer2.tilesAmounts());
		
		Console.message("\n\n\n\n\n\n\n");
		GameBoard gameboard = new GameBoard(10, 9);
		Console.message(gameboard.toAscii());
		
		gameboard.put(new Position(1,1), rackPlayer1.popTile(0));
		Console.message(gameboard.toAscii());
		gameboard.put(new Position(1,2), rackPlayer1.popTile(0));
		Console.message(gameboard.toAscii());
		Console.message(rackPlayer1.tilesAmounts() + "");
	}	
}
