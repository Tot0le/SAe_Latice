package latice.model;

import java.util.ArrayList;
import java.util.Collections;

import latice.model.tile.Tile;

public class Rack {
	private ArrayList<Tile> tiles;
	private final Integer maxTiles;

	public Rack() {
		this.tiles = new ArrayList<>();
		this.maxTiles = 5;
	}

	public Rack(Integer maxTiles) {
		this.tiles = new ArrayList<>();
		this.maxTiles = maxTiles;
	}
	
	public Integer size() {
		return this.tiles.size();
	}

	// getteur
	public int tilesAmount() {
		return tiles.size();
	}

	public Tile popTile(Integer index) {
		Tile tile = getTile(index);
		tiles.remove(getTile(index));
		return tile;

	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public Tile getTile(Integer index) {
		Tile tile;
		if (index < this.tilesAmount()) {
			tile = this.tiles.get(index);
		} else {
			tile = null;
		}
		return tile;
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
	
	public boolean isFull() {
		return this.tilesAmount() >= maxTiles();
	}
	
	public ArrayList<Tile> takeTilesFromRack(ArrayList<Integer> indexesOfTilesInRack) {
		
		ArrayList<Tile> tilesToBeExchanged = new ArrayList<>();
		Collections.sort(indexesOfTilesInRack);
		Collections.reverse(indexesOfTilesInRack);
		
		for (int indexOfTiles : indexesOfTilesInRack) {
			tilesToBeExchanged.add(this.tiles.get(indexOfTiles));
		}
		
		for (int indexOfTiles : indexesOfTilesInRack) {
			this.tiles.remove(indexOfTiles);
		}
		
		return tilesToBeExchanged;
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