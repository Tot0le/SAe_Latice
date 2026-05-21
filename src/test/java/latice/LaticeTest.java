package latice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import latice.gui.Console;
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

class LaticeTest {
	private static Tile RED_DOLPHIN = new Tile(Color.Red, Shape.Dolphin);
	private static Tile NAVY_DOLPHIN = new Tile(Color.Navy, Shape.Dolphin);
	private static Tile GREEN_TURTLE = new Tile(Color.Green, Shape.Turtle);
	private static Tile RED_TURTLE = new Tile(Color.Red, Shape.Turtle);
	private static Tile RED_GECKO = new Tile(Color.Red, Shape.Gecko);
	private static Tile TEAL_FEATHER = new Tile(Color.Teal, Shape.Feather);
	private final Integer nbTileMax = 72 ;
	Rack rack;
	Pool pool;
	GameBoard gameboard;

	@BeforeEach
	void clearElements() {
		rack = new Rack();
		pool = new Pool();
		gameboard = new GameBoard(10, 9);
	}

//	@Disabled
	@Test
	void isATileDrawInTheRack() {
		ArrayList<Tile> ourTestingTiles = new ArrayList<>();
		ourTestingTiles.addAll(List.of(RED_DOLPHIN, NAVY_DOLPHIN, GREEN_TURTLE, RED_TURTLE, RED_GECKO));
		pool.setTiles(ourTestingTiles);

		Player player = new Player(0,rack,pool);
		player.drawATile();
		assertEquals(rack.getTile(0), RED_DOLPHIN);

		assertFalse(pool.contains(RED_DOLPHIN));
		player.drawATile();
		player.drawATile();
		player.drawATile();
		player.drawATile();
		assertTrue(rack.contains(GREEN_TURTLE));
		assertFalse(pool.contains(GREEN_TURTLE));

	}

	@Test
	void playerDrawTiles() {
		ArrayList<Tile> tiles = Factory.createAllTiles();
		Pool bigPool = new Pool(tiles);
		Referee.shuffle(bigPool);
		Pool poolPlayer1 = new Pool();
		Pool poolPlayer2 = new Pool();
		Referee.dealTheCards(bigPool, poolPlayer1, poolPlayer2);

		Rack rackPlayer1 = new Rack();
		Rack rackPlayer2 = new Rack();

		Player player1 = new Player(0, rackPlayer1, poolPlayer1);
		Player player2 = new Player(0, rackPlayer2, poolPlayer2);

		for (int i = 0; i < 5; i++) {
			assertEquals((nbTileMax/2) -i, poolPlayer1.tilesAmounts());
			assertEquals((nbTileMax/2) -i, poolPlayer2.tilesAmounts());
			assertEquals(i, rackPlayer1.tilesAmounts());
			assertEquals(i, rackPlayer2.tilesAmounts());

			player1.drawATile();
			player2.drawATile();
	}
		assertEquals((nbTileMax/2) -5, poolPlayer1.tilesAmounts());
		assertEquals((nbTileMax/2) -5, poolPlayer2.tilesAmounts());
		assertEquals(5, rackPlayer1.tilesAmounts());
		assertEquals(5, rackPlayer2.tilesAmounts());

		player1.drawATile();
		// shouldn't change because the rack is full

		assertEquals((nbTileMax/2) -5, poolPlayer1.tilesAmounts()) ;
		assertEquals(5, rackPlayer1.tilesAmounts());

	}

	@Test
	void placeATileInGameBoard() {

		Pool poolPlayer1 = new Pool();
		Pool poolPlayer2 = new Pool();

		Rack rackPlayer1 = new Rack();
		Rack rackPlayer2 = new Rack();

		ArrayList<Tile> rackPreparation = new ArrayList<>();
		rackPreparation.addAll(List.of(RED_DOLPHIN, RED_TURTLE, TEAL_FEATHER, GREEN_TURTLE));
		rackPlayer1.setTiles(rackPreparation);

		Console.message(gameboard.toAscii());

		assertEquals(4, rackPlayer1.tilesAmounts());
		Position center_4_4 = new Position(1,1);
		gameboard.put(center_4_4, rackPlayer1.popTile(0));
		assertEquals(3, rackPlayer1.tilesAmounts());
		assertEquals(RED_DOLPHIN, gameboard.getTile(center_4_4));

		Position pos_4_5 = new Position(4,5);
		gameboard.put(pos_4_5, rackPlayer1.popTile(0));
		assertEquals(RED_TURTLE, gameboard.getTile(pos_4_5));
		assertEquals(2, rackPlayer1.tilesAmounts());
	}
	
	@Test
	void testingLimitsOfGameBoard() {
		Position pos_7_7 = new Position(7,7);
		gameboard.put(pos_7_7, new Tile(Color.Green, Shape.Turtle));
		assertEquals(GREEN_TURTLE, gameboard.getTile(pos_7_7));

		Position pos_0_0 = new Position(0,0);
		gameboard.put(pos_0_0, new Tile(Color.Green, Shape.Turtle));
		assertEquals(GREEN_TURTLE, gameboard.getTile(pos_0_0));

		Position pos_8_8 = new Position(8,8);
		gameboard.put(pos_8_8, new Tile(Color.Green, Shape.Turtle));
		assertEquals(GREEN_TURTLE, gameboard.getTile(pos_8_8));

		Position pos_8_9 = new Position(8,9);
		gameboard.put(pos_8_9, new Tile(Color.Green, Shape.Turtle));
		assertEquals(null, gameboard.getTile(pos_8_9));

		Position pos_9_8 = new Position(9,8);
		gameboard.put(pos_9_8, new Tile(Color.Green, Shape.Turtle));
		assertEquals(null, gameboard.getTile(pos_9_8));

	}
}
