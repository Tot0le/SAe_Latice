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

	public static void shuffle(Pool pool) {
		pool.shuffle();
		
	}
	
}



