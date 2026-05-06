package latice.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import latice.gui.Console;

public class LaticeApplicationConsole extends Referee{
	private final Integer nbTileMax = 72 ;
	public static void main(String[] args) {
//		Console.title("-- Bienvenue dans notre magnifique jeu de latice ! -- ");
//		Scanner scan = new Scanner(System.in);
//		System.out.println("Veuillez saisir le nombre de joueurs :");
//		int nbJoueurs = scan.nextInt();
//		System.out.println("Il y a " + nbJoueurs+" joueurs");
//		
//		Tile tiletest = new Tile(Color.Green, Shape.Bird);
//		System.out.println(tiletest);
		
		// real game preparation :
		ArrayList<Tile> tiles = Referee.createAllTiles();
		tiles = Referee.shuffle2(tiles);
		Pool poolPlayer1 = new Pool();
		Pool poolPlayer2 = new Pool();
		Pool[] pools = dealTheCards(tiles);
		poolPlayer1.setTiles(pools[0].getTiles());
		poolPlayer2.setTiles(pools[1].getTiles());
//		System.out.println(poolPlayer1);
//		System.out.println(poolPlayer2);
//		System.out.println(poolPlayer1.tilesAmounts());
//		System.out.println(poolPlayer2.tilesAmounts());
		
		Rack rackPlayer1 = new Rack();
		Rack rackPlayer2 = new Rack();
		
		Player player1 = new Player(0, rackPlayer1, poolPlayer1);
		Player player2 = new Player(0, rackPlayer2, poolPlayer2);
		
		for (int i = 0; i < 5; i++) {
			System.out.println("\n\n\n ---- " + i + "eme iteration ----" );
			System.out.println("Rack player 1 : " + rackPlayer1);
			System.out.println("Pool player 1 : \n" + poolPlayer1 + "\nTiles Amount : " + poolPlayer1.tilesAmounts());
			player1.drawATile();
			System.out.println("Rack player 2 : " + rackPlayer2);
			System.out.println("Pool player 2 : \n" + poolPlayer2 + "\nTiles Amount : " + poolPlayer2.tilesAmounts());
			player2.drawATile();
		}
		System.out.println("\n\n\n ---- 5eme iteration ----" );
		System.out.println("Rack player 1 : " + rackPlayer1);
		System.out.println("Pool player 1 : \n" + poolPlayer1 + "\nTiles Amount : " + poolPlayer1.tilesAmounts());
		System.out.println("Rack player 2 : " + rackPlayer2);
		System.out.println("Pool player 2 : \n" + poolPlayer2 + "\nTiles Amount : " + poolPlayer2.tilesAmounts());
		
		
	}	
}
