package latice.model;

public class Referee {
	
	public static Pool[] dealTheCards(Pool pool) {
		Pool pool1 = new Pool();
		Pool pool2 = new Pool();
		Integer tilesNumber = pool.tilesAmounts();
		
		for (int i = 0; i < tilesNumber; i = i + 2) {
			pool1.addTile(pool.getTile(i));
			pool2.addTile(pool.getTile(i + 1));
		}
		
		return new Pool[] { pool1, pool2 };
		
	}

	public static void shuffle(Pool pool) {
		pool.shuffle();
		
	}
	
}



