package latice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import latice.model.tile.Tile;

public class GameBoard {
	private final Integer nbCycle;
	private final Integer width;
	private final Integer height;
	private final Map<Position, Tile> tiles;
	private final Map<Position, SquareType> sunSquares;
	
	public GameBoard(Integer nbCycle, Integer width, Integer height) {
		this.nbCycle = nbCycle;
		this.width = width;
		this.height = height;
		this.tiles = new HashMap<>();
		this.sunSquares = new HashMap<>();
		
		ArrayList<Position> positionSunSquares = createDefaultSunSquarePlacement();
		createsunSquaresWith(positionSunSquares);
		
	}
	
	public ArrayList<Position> createDefaultSunSquarePlacement() {
		ArrayList<Position> positionSunSquares = new ArrayList<Position>();

		for (int i = 0; i<3 ; i++) {
			positionSunSquares.add(new Position(i, i)); // the three top left sun square
			positionSunSquares.add(new Position(i, width-1 - i)); // the three top right sun square
			positionSunSquares.add(new Position(height-1 - i, i)); // the three bottom left sun square
			positionSunSquares.add(new Position(height-1 - i, width-1 - i)); // the three bottom right sun square
		}

		
		positionSunSquares.add(new Position(Math.round(width/2),0)); // left sun
		positionSunSquares.add(new Position(0, Math.round(height/2))); // top sun
		positionSunSquares.add(new Position(Math.round(width/2),height)); //right sun
		positionSunSquares.add(new Position(height, Math.round(height/2))); // bottom sun
		
		return positionSunSquares;
	}
	
	public void createsunSquaresWith(ArrayList<Position> positionList){
		for (Position position : positionList) {
			this.sunSquares.put(position, SquareType.SUN);
		}
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
    	    return this.tiles.remove(position); 
 
     }

     public String toAscii() {  
    	 
         return "";
     }     
     
     
     
     
     
     
     
}
