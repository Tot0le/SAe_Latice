package latice.model;

import java.util.ArrayList;
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
		ArrayList<Player> sortedListOfPlayer = new ArrayList<>();
		Player nextPlayer;
		Integer nbIteration = 0; 
		List<Player> copyPlayers = new ArrayList<>(players);
		Integer initialSize = copyPlayers.size();
		
		for (int i = 0; i < initialSize; i++) {
			nbIteration += 1;
			
			int randomPlayerChoose = (int)(Math.random() * copyPlayers.size());
			
			nextPlayer = copyPlayers.get(randomPlayerChoose);
			nextPlayer.setOrderNumber(nbIteration);
			
			sortedListOfPlayer.add(nextPlayer);
			
			copyPlayers.remove(randomPlayerChoose);
		}
		
		return sortedListOfPlayer;
		
	}

}



