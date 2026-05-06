package latice.application;

import java.util.ArrayList;

public class Pool {
	private ArrayList<Tile> tiles;
	
	public Pool() {
		this.tiles = new ArrayList<Tile>();
	}
	
	public ArrayList<Tile> getTiles() {
		return this.tiles;
	}

	public int tilesAmounts() {
		return tiles.size();
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
	
	public void addTile(Tile tile) {
		this.tiles.add(tile);
	}
	
	@Override
	public String toString() {
		String result = "[";
		for (Tile tile : tiles) {
			result = result + tile + ", ";
		}
		result = result + "]";
		return result;
	}
}
