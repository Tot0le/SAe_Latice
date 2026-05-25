package latice.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
		ArrayList<Player> orderedListOfPlayer = new ArrayList<>();
		
		Integer orderNumber = 0;
		
		Collections.shuffle(orderedListOfPlayer);
		
		for (Player player: orderedListOfPlayer) {
			orderNumber += 1;
			player.setOrderNumber(orderNumber);
		}
		
		return orderedListOfPlayer;
		
	}

}



