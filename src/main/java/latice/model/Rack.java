package latice.model;

import java.util.ArrayList;

import latice.model.tile.Tile;

public class Rack {
	private ArrayList<Tile> tiles;
	private final Integer maxTiles;
	
	public Rack() {
		this.tiles = new ArrayList<Tile>();
		this.maxTiles = 5;
	}
	
	public Rack(Integer maxTiles) {
		this.tiles = new ArrayList<Tile>();
		this.maxTiles = maxTiles;
	}
	
	// getteur
	public int tilesAmounts() {
		return tiles.size();
	}

	public Tile popTile(Integer index) {
		Tile tile = getTile(index);
		tiles.remove(index); //TODO the tile is not remove
		return tile;
		
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

	public Integer maxTiles() {
		return maxTiles;
	}
}