package latice.application;

import java.util.ArrayList;

public class Rack {
	private int tilesAmounts;
	private ArrayList<Tile> tiles;

	public void addTile(Tile tile) {
        this.tiles.add(tile);
    }
	// getteur
	public int getTilesAmounts() {
		return tilesAmounts;
	}

	// setteur
	public void setTilesAmounts(int tilesAmounts) {
		this.tilesAmounts = tilesAmounts;
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
}