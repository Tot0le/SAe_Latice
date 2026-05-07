package latice.model;

import java.util.Map;

import latice.model.tile.Tile;

import java.util.HashMap;

public class GameBoard {
	private final Integer nbCycle;
	private final Map<Position, Tile> tiles;
	
	public GameBoard(Integer nbCycle) {
		this.tiles = new HashMap<>();
		this.nbCycle = nbCycle;
	}

	public Integer getNbCycle() {
		return nbCycle;
	}


    public Map<Position, Tile> tiles() {
         return tiles;
     }


    public boolean isTileAt(Position position) {
        return tiles.containsKey(position);
     }

     public boolean isEmpty() {
         return tiles.isEmpty();
    }

     public boolean put(Position position, Tile disc) {
         if (! this.isTileAt(position)) {
             this.tiles.put(position, disc);
             return true;
         }
         return false;
     }

     public void clear() {
         this.tiles.clear();
     }

     public int howManyTilesOnBoard() {
         return this.tiles.size();
     }

     public Tile discAt(Position position) {
         return this.tiles.get(position);
     }

     public Tile removeTileAt(Position position) {
    	 Tile previousDisc = this.discAt(position);
 		 this.tiles.remove(previousDisc);
 		 return previousDisc;
     }

     public String toAscii() {                                                                                                                                                                              
         return "";
         // TODO
     }                           
}
