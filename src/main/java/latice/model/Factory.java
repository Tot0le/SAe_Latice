package latice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import latice.model.tile.Color;
import latice.model.tile.Shape;
import latice.model.tile.Tile;

public class Factory {
	
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
	
	public static void createSpecialSquares(Integer lenght, Map<Position, SquareType> specialSquares, Position moonSquarePosition) {
		// Combine all the create methods to build and place all the special squares
		ArrayList<Position> positionSpecialSquares = createSunSquarePlacementList(lenght);
		createSunSquaresWith(positionSpecialSquares, specialSquares);
		createMoonSquare(specialSquares, moonSquarePosition);
		
	}
	
	public static void createMoonSquare(Map<Position, SquareType> specialSquares, Position moonSquarePosition) {
		specialSquares.put(moonSquarePosition, SquareType.MOON);
	}
	
	public static ArrayList<Position> createSunSquarePlacementList(Integer lenght) {
		ArrayList<Position> positionSpecialSquares = new ArrayList<Position>();

		for (int i = 0; i<3 ; i++) {
			positionSpecialSquares.add(new Position(i, i)); // the three top left sun square
			positionSpecialSquares.add(new Position(i, lenght-1 - i)); // the three top right sun square
			positionSpecialSquares.add(new Position(lenght-1 - i, i)); // the three bottom left sun square
			positionSpecialSquares.add(new Position(lenght-1 - i, lenght-1 - i)); // the three bottom right sun square
		}

		
		positionSpecialSquares.add(new Position(Math.round((lenght-1)/2),0)); // left sun
		positionSpecialSquares.add(new Position(0, Math.round((lenght-1)/2))); // top sun
		positionSpecialSquares.add(new Position(Math.round(lenght/2),lenght-1)); //right sun
		positionSpecialSquares.add(new Position(lenght-1, Math.round((lenght -1)/2))); // bottom sun
		
		
		return positionSpecialSquares;
	}
	
	public static void createSunSquaresWith(ArrayList<Position> positionList, Map<Position, SquareType> specialSquares){
		for (Position position : positionList) {
			specialSquares.put(position, SquareType.SUN);
		}
	}
}
