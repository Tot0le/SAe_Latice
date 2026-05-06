package latice.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Referee {
	

	public static ArrayList<Tile> createAllTiles(){
		ArrayList<Shape> shapes = new ArrayList<>(List.of(Shape.Dolphin, Shape.Bird, Shape.Feather, 
				Shape.Flower, Shape.Gecko, Shape.Turtle));
		
		ArrayList<Color> colors = new ArrayList<>(List.of(Color.Green, Color.Magenta, Color.Navy, 
				Color.Red, Color.Teal, Color.Yellow));
		
		ArrayList<Tile> tiles = new ArrayList<>();
		
		for (Shape shape : shapes) {
			for (Color color : colors) {
				tiles.add(new Tile(color, shape));
				tiles.add(new Tile(color, shape));
			}
		}
		
		return tiles;
	}
	
	public ArrayList<Tile> shuffle(ArrayList<Tile> tiles, Integer nbTiles) {
		
		ArrayList<Tile> shuffledTiles = new ArrayList<>();
		Random nbTileRandom = new Random();
		Integer nbTilesTotal = tiles.size();
		
		for (Tile tile : tiles) {
			int tileToIndex = nbTileRandom.nextInt(nbTilesTotal);
			Tile tempTile = tiles.get(tileToIndex);
			shuffledTiles.add(tempTile);
			tiles.remove(tileToIndex);
		}
		return shuffledTiles;
		
	}
public void pool2making(ArrayList<Tile> tiles) {
		
		ArrayList<Tile> pool1 = new ArrayList<>();
		ArrayList<Tile> pool2 = new ArrayList<>();
		Random nbTileRandom = new Random();
		Integer nbTilesPerPool = tiles.size()/2;
		
		for (int i = 0;nbTilesPerPool>0;) {
			int randomTileFromTiles = nbTileRandom.nextInt(nbTilesPerPool);
			Tile tempTile = tiles.get(randomTileFromTiles);
			pool1.add(tempTile);
			tiles.remove(randomTileFromTiles);
			
		}
	pool2.addAll(tiles);
	}
}



