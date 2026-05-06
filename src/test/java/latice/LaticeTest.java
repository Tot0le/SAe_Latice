package latice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import latice.application.Color;
import latice.application.Pool;
import latice.application.Rack;
import latice.application.Referee;
import latice.application.Shape;
import latice.application.Tile;

public class LaticeTest {
	private static Tile RED_DOLPHIN = new Tile(Color.Red, Shape.Dolphin);
	private static Tile NAVY_DOLPHIN = new Tile(Color.Navy, Shape.Dolphin);
	private static Tile GREEN_TURTLE = new Tile(Color.Green, Shape.Turtle);
	private static Tile RED_TURTLE = new Tile(Color.Red, Shape.Turtle);
	private static Tile RED_GECKO = new Tile(Color.Red, Shape.Gecko);
	Rack rack;
	Pool pool;
	
	@BeforeEach
	public void clearGameBoard() {
		rack = new Rack();
		pool = new Pool();
	}
	
	@Disabled
	@Test
	public void isATileDrawInTheRack() {
		Object tiles = Referee.createAllTiles();
		Referee.shuffle();
//		Tile tiletest = new Tile(Color.Green, Shape.Bird);
//		System.out.println(tiletest);
//		Rack rack = new Rack();
//		ArrayList<Tile> tiles = Referee.createAllTiles();
//		rack.setTiles(tiles);
//		System.out.println(rack);
	}
}
