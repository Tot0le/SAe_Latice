package latice.model;

import java.util.HashMap;
import java.util.Map;
import latice.gui.Console;
import latice.model.tile.Tile;

public class GameBoard {
	private final Integer nbCycle;
	private final Integer length;
	private final Map<Position, Tile> tiles;
	private final Map<Position, SquareType> specialSquares;
	private final Position moonSquarePosition;
	
	public GameBoard() {
		this.nbCycle = 10;
		this.length = 9;
		this.tiles = new HashMap<>();
		this.specialSquares = new HashMap<>();
		this.moonSquarePosition = new Position(Math.round((length-1)/2), Math.round((length-1)/2));
		
		Factory.createSpecialSquares(length, specialSquares, moonSquarePosition);
	}
	
	public GameBoard(Integer nbCycle) {
		this.nbCycle = nbCycle;
		this.length = 9;
		this.tiles = new HashMap<>();
		this.specialSquares = new HashMap<>();
		this.moonSquarePosition = new Position(Math.round((length-1)/2), Math.round((length-1)/2));
		
		Factory.createSpecialSquares(length, specialSquares, moonSquarePosition);
	}
	
	public GameBoard(Integer nbCycle, Integer lenght) {
		this.nbCycle = nbCycle;
		this.length = lenght;
		this.tiles = new HashMap<>();
		this.specialSquares = new HashMap<>();
		this.moonSquarePosition = new Position(Math.round((lenght-1)/2), Math.round((lenght-1)/2));
		
		Factory.createSpecialSquares(lenght, specialSquares, moonSquarePosition);
	}
	
	public Integer getNbCycle() {
		return nbCycle;
	}

	public Tile getTile(Position position) {
		return tiles.get(position);
	}
	
    public Map<Position, Tile> tiles() {
         return tiles;
     }

    public boolean isTileAt(Position position) {
        return tiles.containsKey(position);
     }
    
    public boolean isSunAt(Position position) {
    	return specialSquares.get(position) == SquareType.SUN;
    }
    
    public boolean isMoonAt(Position position) {
    	return specialSquares.get(position) == SquareType.MOON;
    }
    
     public boolean isEmpty() {
         return tiles.isEmpty();
    }

     public boolean put(Position position, Tile tile) {
         if (! this.isTileAt(position)) {
             this.tiles.put(position, tile);
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
    	 Position currentPos;
    	 String result = "";
    	 
    	 for (int i = 0; i < length; i++) {
    		 for (int j = 0; j < length; j++) {
    			 currentPos = new Position(j,i);
    			 
    			 if (isTileAt(currentPos)) {
    				 
    				 if (isSunAt(currentPos)) {
        				 result = result + Console.ANSI_YELLOW + "[" + this.getTile(currentPos) + "]" + Console.ANSI_RESET;
        			 } else if (isMoonAt(currentPos)) { 
        				 result = result + Console.ANSI_PURPLE + "[" + this.getTile(currentPos) + "]" + Console.ANSI_RESET;
        			 } else {
        				 result = result + "[" + this.getTile(currentPos) + "]";
        			 }
    			 } else {
    				 
    				 if (isSunAt(currentPos)) {
        				 result = result + Console.ANSI_YELLOW + "[" + "]" + Console.ANSI_RESET;
    				 } else if (isMoonAt(currentPos)) { 
        				 result = result + Console.ANSI_PURPLE + "[" + "]" + Console.ANSI_RESET;
        			 } else {
        				 result = result + "[" + "]";
        			 }
    			 }
    			 
    		 }
    		 result = result + "\n";
    	 }
         return result;
     }

	 public Integer getLength() {
		 return length;
	 }

	 public Map<Position, Tile> getTiles() {
		 return tiles;
	 }

	 public Map<Position, SquareType> getSpecialSquares() {
		 return specialSquares;
	 }

	 public Position getMoonSquarePosition() {
		 return moonSquarePosition;
	 }     

}
