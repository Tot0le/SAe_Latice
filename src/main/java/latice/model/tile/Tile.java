package latice.model.tile;

import latice.util.ImagePath;

public class Tile {
	private final Color color;
	private final Shape shape;
	private final ImagePath path;

	public Tile(Color color, Shape shape){
		this.color = color;
		this.shape = shape;
		this.path = ImagePath.getImagePath(shape, color);
	}
	
	public Color color() {
		return color;
	}
	public Shape shape() {
		return shape;
	}
	public ImagePath path() {
		return path;
	}
	
	@Override
	public String toString() {
		return this.color + " " + this.shape;
	}
}