package latice.application;

import java.util.ArrayList;

public class Pool {
	private ArrayList<Tile> tiles;
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	
	public Tile drawTile() {
		if(this.tiles.size() > 0) {
			Tile returnTile = this.tiles.getFirst();
			this.tiles.removeFirst();
			
			return returnTile;
		}else {
			return null;
		}
	}
}
