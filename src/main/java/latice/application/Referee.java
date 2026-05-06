package latice.application;

import java.util.ArrayList;
import java.util.List;

import latice.application.Color;
import latice.application.Shape;
import latice.application.Tile;

public class Referee {
	
	public ArrayList<Tile> createAllTiles(){
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
	
	public void shuffle(ArrayList<Tile> tiles) {
		//TODO first
	}
}
