package latice.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import latice.model.tile.Color;
import latice.model.tile.Shape;
import latice.model.tile.Tile;

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
	
	public static ArrayList<Tile> shuffle(ArrayList<Tile> tiles) {
		
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
		System.out.println("Pool 1  : "+ pool1 +".");
		System.out.println("Pool 2  : "+ pool2 +".");
	}
	
	// second version in case it doesn't work properly
	public static Pool[] dealTheCards(ArrayList<Tile> tiles) {
		Pool pool1 = new Pool();
		Pool pool2 = new Pool();
		Integer tilesNumber = tiles.size();
		
		for (int i = 0; i < tilesNumber; i = i + 2) {
			pool1.addTile(tiles.get(i));
			pool2.addTile(tiles.get(i + 1));
		}
		
		return new Pool[] { pool1, pool2 };
		
	}
	// second version in case it doesn't work properly
	public static ArrayList<Tile> shuffle2(ArrayList<Tile> tiles) {
		Collections.shuffle(tiles);
		return tiles;
		
	}
	
}



