package latice.model.tile;

public class Tile {
	private final Color color;
	private final Shape shape;
	//TODO add directly an ImagePath here and then modify in Factory the tiles creations
	
	public Tile(Color color, Shape shape){
		this.color = color;
		this.shape = shape;
	}
	public Color getColor() {
		return color;
	}
	public Shape getShape() {
		return shape;
	}
	
	@Override
	public String toString() {
		return this.color + " " + this.shape;
	}
}