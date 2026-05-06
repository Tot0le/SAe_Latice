package latice.application;

import java.util.ArrayList;

public class Rack {
	private ArrayList<Tile> tiles;

<<<<<<< HEAD
	public void addTile(Tile tile) {
        this.tiles.add(tile);
    }
=======
>>>>>>> dd019042dae2d2fd054a07bde6ddc254f36c659e
	// getteur
	public int tilesAmounts() {
		return tiles.size();
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
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