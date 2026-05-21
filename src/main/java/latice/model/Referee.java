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
		Player nexPlayer;
		Integer nbIteration = 0; 
		ArrayList<Player> copyPlayers = (ArrayList<Player>) players;
		
		for (Player player : players) {
			nbIteration += 1;
			
			int randomPlayerChoose = (int)(Math.random() * players.size());
			
			nexPlayer = copyPlayers.get(randomPlayerChoose);
			nexPlayer.setOrderNumber(nbIteration);
			
			sortedListOfPlayer.add(nexPlayer);
			
			copyPlayers.remove(randomPlayerChoose);
		}
		
		return sortedListOfPlayer;
		
	}

}



