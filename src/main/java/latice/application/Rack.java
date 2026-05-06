package latice.application;

import java.util.ArrayList;

public class Rack {
	private ArrayList<Tile> tiles;
	
	public Rack() {
		this.tiles = new ArrayList<Tile>();
	}
	// getteur
	public int tilesAmounts() {
		return tiles.size();
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	public Tile getTile(Integer index) {
		return this.tiles.get(index);
	}
	
	public boolean contains(Tile tile) {
		return this.tiles.contains(tile);
	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
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