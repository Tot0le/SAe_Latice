package latice.application;

import java.util.ArrayList;

public class Pool {
	private ArrayList<Tile> tiles;
	
	public ArrayList<Tile> getTiles() {
		return this.tiles;
	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	
	public Tile getTile(Integer index) {
		return this.tiles.get(index);
	}
	
	public boolean contains(Tile tile) {
		return this.tiles.contains(tile);
	}
}
