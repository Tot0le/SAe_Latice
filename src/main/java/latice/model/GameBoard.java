package latice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import latice.gui.Console;
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
		createSunSquaresWith(positionSunSquares);
		
	}
	
	public ArrayList<Position> createDefaultSunSquarePlacement() {
		ArrayList<Position> positionSunSquares = new ArrayList<Position>();

		for (int i = 0; i<3 ; i++) {
			positionSunSquares.add(new Position(i, i)); // the three top left sun square
			positionSunSquares.add(new Position(i, width-1 - i)); // the three top right sun square
			positionSunSquares.add(new Position(height-1 - i, i)); // the three bottom left sun square
			positionSunSquares.add(new Position(height-1 - i, width-1 - i)); // the three bottom right sun square
		}

		
		positionSunSquares.add(new Position(Math.round((width-1)/2),0)); // left sun
		positionSunSquares.add(new Position(0, Math.round((height-1)/2))); // top sun
		positionSunSquares.add(new Position(Math.round(width/2),height-1)); //right sun
		positionSunSquares.add(new Position(height-1, Math.round((height -1)/2))); // bottom sun
		
		return positionSunSquares;
	}
	
	public void createSunSquaresWith(ArrayList<Position> positionList){
		for (Position position : positionList) {
			this.sunSquares.put(position, SquareType.SUN);
		}
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
    	return sunSquares.containsKey(position);
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
    	 
    	 for (int i = 0; i < height; i++) {
    		 for (int j = 0; j < width; j++) {
    			 currentPos = new Position(j,i);
    			 
    			 if (isTileAt(currentPos)) {
    				 
    				 if (isSunAt(currentPos)) {
        				 result = result + Console.ANSI_YELLOW + "[" + this.getTile(currentPos) + "]" + Console.ANSI_RESET;
        			 } else {
        				 result = result + "[" + this.getTile(currentPos) + "]";
        			 }
    			 } else {
    				 
    				 if (isSunAt(currentPos)) {
        				 result = result + Console.ANSI_YELLOW + "[" + "]" + Console.ANSI_RESET;
        			 } else {
        				 result = result + "[" + "]";
        			 }
    			 }
    			 
    		 }
    		 result = result + "\n";
    	 }
         return result;
     }     

}
