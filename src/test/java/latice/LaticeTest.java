package latice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import latice.model.Player;
import latice.model.Pool;
import latice.model.Rack;
import latice.model.tile.Color;
import latice.model.tile.Shape;
import latice.model.tile.Tile;

public class LaticeTest {
	private static Tile RED_DOLPHIN = new Tile(Color.Red, Shape.Dolphin);
	private static Tile NAVY_DOLPHIN = new Tile(Color.Navy, Shape.Dolphin);
	private static Tile GREEN_TURTLE = new Tile(Color.Green, Shape.Turtle);
	private static Tile RED_TURTLE = new Tile(Color.Red, Shape.Turtle);
	private static Tile RED_GECKO = new Tile(Color.Red, Shape.Gecko);
	Rack rack;
	Pool pool;
	
	@BeforeEach
	public void clearElements() {
		rack = new Rack();
		pool = new Pool();
	}
	
//	@Disabled
	@Test
	public void isATileDrawInTheRack() {
		
		ArrayList<Tile> ourTestingTiles = new ArrayList<>();
		ourTestingTiles.addAll(List.of(RED_DOLPHIN, NAVY_DOLPHIN, GREEN_TURTLE, RED_TURTLE, RED_GECKO));
		pool.setTiles(ourTestingTiles);
		
		Player player = new Player(0,rack,pool);
		player.drawATile();
		assertTrue(rack.getTile(0) == RED_DOLPHIN);
		
		assertFalse(pool.contains(RED_DOLPHIN));
		player.drawATile();
		player.drawATile();
		player.drawATile();
		player.drawATile();
		assertTrue(rack.contains(GREEN_TURTLE));
		assertFalse(pool.contains(GREEN_TURTLE));
		
	}
}
