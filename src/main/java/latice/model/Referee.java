package latice.model;

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
	
}



