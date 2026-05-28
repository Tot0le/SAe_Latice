package latice.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import latice.model.tile.Color;
import latice.model.tile.Shape;
import latice.model.tile.Tile;

public class Referee {

	public static void dealTheCards(Pool originPool, Pool poolPlayer1, Pool poolPlayer2) {
		Integer tilesNumber = originPool.tilesAmounts();

		for (int i = 0; i < tilesNumber; i = i + 2) {
			poolPlayer1.addTile(originPool.getTile(i));
			poolPlayer2.addTile(originPool.getTile(i + 1));
		}
	}

	public static void shuffle(Pool pool) {
		pool.shuffle();

	}
	
	public static List<Player> randomChoosePlayerOrder(List<Player> players) {
		ArrayList<Player> orderedListOfPlayer = new ArrayList<>(players); // ordered means by the play order of the player
		
		Integer orderNumber = 0;
		
		Collections.shuffle(orderedListOfPlayer);
		
		for (Player player: orderedListOfPlayer) {
			orderNumber += 1;
			player.setOrderNumber(orderNumber);
		}

		return orderedListOfPlayer;
		
	}
	
	public static boolean checkIfMoveIsLegal(List<Tile> nearbyTiles, GameBoard gameboard, Position selectedTilePosition, Tile selectedTile ) {
		boolean isLegal = true;
		if (gameboard.getTile(selectedTilePosition) != null) {
			isLegal = false;
		} else if (nearbyTiles.isEmpty() && ! gameboard.isEmpty() || ! gameboard.isMoonAt(selectedTilePosition) && gameboard.isEmpty() ){
			isLegal = false;
		} else {
			for (Tile tile : nearbyTiles) {
				if (tile.color() != selectedTile.color() && tile.shape() != selectedTile.shape()) {
					isLegal = false;
				}
			}
		}
		return isLegal;
	}


	public static Integer calculatePoints(ArrayList<Tile> nearbyTiles, Tile tilePlaced) {
		Integer numberOfTileMatches = 0;
		Integer points = 0;
		Color colorOfTilePlaced = tilePlaced.color();
		Shape shapeOfTilePlaced = tilePlaced.shape();
		
		for (Tile tile : nearbyTiles) {
			if (tile.color() == colorOfTilePlaced || tile.shape() == shapeOfTilePlaced) {
				numberOfTileMatches += 1;
			}
		}
		if (numberOfTileMatches == 2) {
			points = 1;
		}else if (numberOfTileMatches == 3) {
			points = 2;
		}else if (numberOfTileMatches == 4) {
			points = 4;
		}
		
		return points;
	}


}



