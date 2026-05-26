package latice.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import latice.gui.model.GameBoardIhm;
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
	
	public static boolean checkIfMoveIsLegal(ArrayList<Tile> nearbyTiles, GameBoard gameboard, Position gameboardPosition, Tile selectedTile ) {
		boolean isLegal = true;
		//System.out.println(nearbyTiles);
		if (nearbyTiles.isEmpty() && ! gameboard.isEmpty() || ! gameboard.isMoonAt(gameboardPosition) && gameboard.isEmpty() ){
			isLegal = false;
		}
		if (isLegal) {
			//System.out.println(nearbyTiles);
			for (Tile tile : nearbyTiles) {
				//System.out.println(selectedTile);
				if (tile.color() != selectedTile.color() && tile.shape() != selectedTile.shape()) {
					isLegal = false;
					//System.out.println("here");
				}
	
			}
		}
	return isLegal;
	}

}



