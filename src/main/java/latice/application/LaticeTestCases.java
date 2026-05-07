package latice.application;

import java.util.ArrayList;
import java.util.List;

import latice.gui.Console;

public class LaticeTestCases {
	private static Tile RED_DOLPHIN = new Tile(Color.Red, Shape.Dolphin);
	private static Tile NAVY_DOLPHIN = new Tile(Color.Navy, Shape.Dolphin);
	private static Tile GREEN_TURTLE = new Tile(Color.Green, Shape.Turtle);
	private static Tile RED_TURTLE = new Tile(Color.Red, Shape.Turtle);
	private static Tile RED_GECKO = new Tile(Color.Red, Shape.Gecko);
	
	
	public static void main(String[] args) {
		Rack rack = new Rack();
		Pool pool = new Pool();
		
		ArrayList<Tile> ourTestingTiles = new ArrayList<>();
		ourTestingTiles.addAll(List.of(RED_DOLPHIN, NAVY_DOLPHIN, GREEN_TURTLE, RED_TURTLE, RED_GECKO));
		pool.setTiles(ourTestingTiles);
		
		Player player = new Player(0,rack,pool);
		player.drawATile();
		Console.message(rack.getTile(0) + " = " + RED_DOLPHIN);
		
		Console.message(pool + " not contain " + RED_DOLPHIN + " this condition is " + !pool.contains(RED_DOLPHIN));
		player.drawATile();
		player.drawATile();
		player.drawATile();
		player.drawATile();
		Console.message(rack + " contain " + GREEN_TURTLE + " this condition is " + rack.contains(GREEN_TURTLE));
		Console.message(pool + " not contain " + GREEN_TURTLE + " this condition is " + !pool.contains(GREEN_TURTLE));
	}

}
