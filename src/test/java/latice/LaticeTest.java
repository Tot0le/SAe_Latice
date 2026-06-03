package latice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;
import latice.gui.Console;
import latice.model.Factory;
import latice.model.GameBoard;
import latice.model.Player;
import latice.model.Pool;
import latice.model.Position;
import latice.model.Rack;
import latice.model.Referee;
import latice.model.StandartPool;
import latice.model.tile.Color;
import latice.model.tile.Shape;
import latice.model.tile.Tile;
import latice.util.ImageLoader;
import latice.util.ImagePath;
import latice.gui.model.GameBoardIhm;

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
	Pool poolPlayer1;
	Pool poolPlayer2;
	Rack rackPlayer1;
	Rack rackPlayer2;
	Player player1;
	Player player2;
	ArrayList<Player> playerList;

	@BeforeEach
	void clearElements() {
		rack = new Rack();
		pool = new Pool();
		gameboard = new GameBoard(10, 9);

		poolPlayer1 = new Pool();
		poolPlayer2 = new Pool();
		
		rackPlayer1 = new Rack();
		rackPlayer2 = new Rack();

		player1 = new Player("Username 1", 0, rackPlayer1, poolPlayer1, gameboard);
		player2 = new Player("Username 2", 0, rackPlayer2, poolPlayer2, gameboard);
		
		player1.drawMaxTile();
		player2.drawMaxTile();
		
		playerList = new ArrayList<>();
		playerList.add(player1);
		playerList.add(player2);
	}

//	@Disabled
	@Test
	void isATileDrawInTheRack() {
		ArrayList<Tile> ourTestingTiles = new ArrayList<>();
		ourTestingTiles.addAll(List.of(RED_DOLPHIN, NAVY_DOLPHIN, GREEN_TURTLE, RED_TURTLE, RED_GECKO));
		pool.setTiles(ourTestingTiles);

		Player player = new Player(0,rack, pool, gameboard);
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

		Referee.dealTheCards(bigPool, poolPlayer1, poolPlayer2);

		player1 = new Player(0, rackPlayer1, poolPlayer1, gameboard);
		player2 = new Player(0, rackPlayer2, poolPlayer2, gameboard);

		for (int i = 0; i < 5; i++) {
			assertEquals((nbTileMax/2) -i, poolPlayer1.tilesAmounts());
			assertEquals((nbTileMax/2) -i, poolPlayer2.tilesAmounts());
			assertEquals(i, rackPlayer1.size());
			assertEquals(i, rackPlayer2.size());

			player1.drawATile();
			player2.drawATile();
	}
		assertEquals((nbTileMax/2) -5, poolPlayer1.tilesAmounts());
		assertEquals((nbTileMax/2) -5, poolPlayer2.tilesAmounts());
		assertEquals(5, rackPlayer1.size());
		assertEquals(5, rackPlayer2.size());

		player1.drawATile();
		// shouldn't change because the rack is full

		assertEquals((nbTileMax/2) -5, poolPlayer1.tilesAmounts()) ;
		assertEquals(5, rackPlayer1.size());

	}

	@Test
	void placeATileInGameBoard() {

		ArrayList<Tile> rackPreparation = new ArrayList<>();
		rackPreparation.addAll(List.of(RED_DOLPHIN, RED_TURTLE, TEAL_FEATHER, GREEN_TURTLE));
		rackPlayer1.setTiles(rackPreparation);

		Console.message(gameboard.toAscii());

		assertEquals(4, rackPlayer1.size());
		Position center_4_4 = new Position(1,1);
		gameboard.put(center_4_4, rackPlayer1.popTile(0));
		assertEquals(3, rackPlayer1.size());
		assertEquals(RED_DOLPHIN, gameboard.getTile(center_4_4));

		Position pos_4_5 = new Position(4,5);
		gameboard.put(pos_4_5, rackPlayer1.popTile(0));
		assertEquals(RED_TURTLE, gameboard.getTile(pos_4_5));
		assertEquals(2, rackPlayer1.size());
		
		String testDescription = "Red Dolphin";
		assertEquals(testDescription, RED_DOLPHIN.toString());
	}
	
	@Test
	void testingNumberOfCycles() {
		assertEquals(10, gameboard.getNbCycle());
	}
	@Test
	void testGameboardClearAndTileEmpty() {
		Position center_5_5 = new Position(5,5);
		assertTrue(gameboard.isEmpty());
		gameboard.put(center_5_5, RED_DOLPHIN);
		assertFalse(gameboard.isEmpty());
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
	
	@Test
	void testGoodBackgroundImage() {
		Image imageSea = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_SEA.imagePath(), 100, 100);
		Image imageSun = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_SUN.imagePath(), 100, 100);
		Image imageMoon = ImageLoader.loadImageSafe(ImagePath.BACKGROUND_MOON.imagePath(), 100, 100);
		GameBoardIhm ihm = new GameBoardIhm(gameboard);
		Position pos_6_7 = new Position(6, 7);
		Position pos_5_5 = new Position(5, 5);
		Position pos_5_9 = new Position(5, 9);
		assertEquals(imageSea.getUrl(), ihm.chooseRightBackgroundImage(pos_6_7).getUrl());
		assertEquals(imageSun.getUrl(), ihm.chooseRightBackgroundImage(pos_5_5).getUrl());
		assertEquals(imageMoon.getUrl(), ihm.chooseRightBackgroundImage(pos_5_9).getUrl());
	}
	
	@Test 
	void testNearbyPositionsAndTiles(){
		Position pos_5_5 = new Position(5, 5);
		Position pos_6_5 = new Position(6,5);
		Position pos_4_5 = new Position(4, 5);
		Position pos_5_6 = new Position(5, 6);
		Position pos_5_4 = new Position(5, 4);
		ArrayList<Position> listPositions = new ArrayList<>();
		listPositions.add(pos_6_5);
		listPositions.add(pos_4_5);
		listPositions.add(pos_5_6);
		listPositions.add(pos_5_4);
		assertEquals(listPositions, pos_5_5.getNearbyPositions());
		
		Tile greenTurtle = new Tile(Color.Green, Shape.Turtle);
		Tile greenDolphin = new Tile(Color.Green, Shape.Dolphin);
		Tile greenBird= new Tile(Color.Green, Shape.Bird);
		Tile greenFeather = new Tile(Color.Green, Shape.Feather);
		Tile greenGecko = new Tile(Color.Green, Shape.Gecko);
		gameboard.put(pos_5_5, greenTurtle);
		gameboard.put(pos_6_5, greenDolphin);
		gameboard.put(pos_4_5, greenBird);
		gameboard.put(pos_5_6, greenFeather);
		gameboard.put(pos_5_4, greenGecko);
		ArrayList<Tile> listTiles = new ArrayList<>();
		listTiles.add(greenDolphin);
		listTiles.add(greenBird);
		listTiles.add(greenFeather);
		listTiles.add(greenGecko);
		assertEquals(listTiles, gameboard.getNearbyTiles(pos_5_5));	
	}
	
	@Test
	void testingTheRefereeBehavior() {
		player1.setPoints(2);
		player2.setPoints(2);
		List<Player> listPlayers1 = new ArrayList<>();
		List<Player> listPlayers2 = new ArrayList<>();
		boolean randomBoolean = false;
		listPlayers1.add(player2);
		listPlayers1.add(player1);
		listPlayers2.add(player1);
		listPlayers2.add(player2);
		
		List<Player> listOrdered = Referee.randomChoosePlayerOrder(listPlayers1);
		if(listOrdered.equals(listPlayers1) || listOrdered.equals(listPlayers2)) {
			randomBoolean = true;
		}
		ArrayList<Tile> ourTestingTiles = new ArrayList<>();
		ourTestingTiles.addAll(List.of(RED_DOLPHIN, NAVY_DOLPHIN, GREEN_TURTLE, RED_TURTLE, RED_GECKO));
		poolPlayer1.setTiles(ourTestingTiles);
		assertTrue(randomBoolean);
		player1.drawMaxTile();
		assertEquals(5, player1.rack().getTiles().size());
	}

	@Test
	void testForALegalMove() {
		Tile greenGecko = new Tile(Color.Green, Shape.Gecko);
		Tile tealDolphin = new Tile(Color.Teal, Shape.Dolphin);
		Tile magentaDolphin = new Tile(Color.Magenta, Shape.Dolphin);
		
		Position pos_6_6 = new Position(6, 6);
		Position pos_7_6 = new Position(7,6);
		Position pos_6_7 = new Position(6, 7);
		Position pos_6_5 = new Position(6, 5);
		Position pos_5_6 = new Position(5, 6);
		
		List<Tile> listNearbyTiles = new ArrayList<>();
		listNearbyTiles.add(RED_DOLPHIN);
		listNearbyTiles.add(NAVY_DOLPHIN);
		listNearbyTiles.add(tealDolphin);
		listNearbyTiles.add(magentaDolphin);
		
		gameboard.put(pos_7_6, RED_DOLPHIN);
		gameboard.put(pos_6_7, NAVY_DOLPHIN);
		gameboard.put(pos_6_5, tealDolphin);
		gameboard.put(pos_5_6, magentaDolphin);
		
		player1.drawMaxTile();
		player2.drawMaxTile();
		
		Referee referee = new Referee(gameboard, playerList);
		referee.setCurrentPlayer(player1);
		referee.currentPlayer().rack().addTile(greenGecko);
		ArrayList<Tile> nearbyTiles = (ArrayList<Tile>) this.gameboard.getNearbyTiles(pos_6_6);
		Tile selectedTile = referee.currentPlayer().rack().getTile(0);
		boolean testResult = referee.checkIfMoveIsLegal(pos_6_6, nearbyTiles, selectedTile); // attempt to put greenGecko to pos 6_6
		assertFalse(testResult);
		
	}
}
