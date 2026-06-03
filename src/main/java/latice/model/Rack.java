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
	
	// getteur
	public int size() {
		return (int) this.tiles.stream().filter(java.util.Objects::nonNull).count();
	}

	public boolean isFull() {
		return this.size() >= maxTiles();
	}

	public Tile popTile(Integer index) {
		Tile tile = getTile(index);
		tiles.set(index, null);
		return tile;

	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public Tile getTile(Integer index) {
		Tile tile = null;
		if (index < this.tiles.size()) {
			tile = this.tiles.get(index);
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
		boolean tileAdded = false;
		
		// search for the null in the rack if exist
		for (int i = 0; i < this.tiles.size(); i++) {
			if (this.tiles.get(i) == null && !tileAdded) {
				this.tiles.set(i, tile);
				tileAdded = true;
			}
		}
		if (!tileAdded) {
			this.tiles.add(tile);
		}
	}
	
	public ArrayList<Tile> takeTilesFromRack(ArrayList<Integer> indexesOfTilesInRack) {
		
		ArrayList<Tile> tilesToBeExchanged = new ArrayList<>();
		Collections.sort(indexesOfTilesInRack);
		Collections.reverse(indexesOfTilesInRack);
		
		for (int indexOfTiles : indexesOfTilesInRack) {
			tilesToBeExchanged.add(this.tiles.get(indexOfTiles));
		}
		
		for (int indexOfTiles : indexesOfTilesInRack) {
			this.tiles.set(indexOfTiles, null);
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