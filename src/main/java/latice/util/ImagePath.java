package latice.util;

import latice.model.tile.Color;
import latice.model.tile.Shape;

public enum ImagePath {
	BACKGROUND_SEA("/images/bg_sea.png", Color.None, Shape.None),
	BACKGROUND_MOON("/images/bg_moon.png", Color.None, Shape.None),
	BACKGROUND_SUN("/images/bg_sun.png", Color.None, Shape.None),

	// Menu related image
	BACKGROUND_MENU("/images/latice_background.png", Color.None, Shape.None),
	QUIT_BUTTON("/images/quit_button.png", Color.None, Shape.None),
	PLAY_BUTTON("/images/play_button.png", Color.None, Shape.None),
	RULES_BUTTON("/images/rules_button.png", Color.None, Shape.None),
	RULES_BACKGROUND("/images/rules.png", Color.None, Shape.None),
	LAUNCH_BUTTON("/images/launch_button.png", Color.None, Shape.None),
	BACK_BUTTON("/images/back_button.png", Color.None, Shape.None),
	
	// Rack cell image
	RACK_CELL("/images/rack_cell.png", Color.None, Shape.None);


	private String imagePath;
	private Color color;
	private Shape shape;

	private	ImagePath(String path, Color color, Shape shape) {
		this.imagePath = path;
		this.color = color;
		this.shape = shape;
	}

	public static String getImagePath(Shape shape, Color color) {
		return "/images/tiles/"+ shape.getNameInPath() + "_" + color.getNameInPath() + ".png";
	}
	
	public String imagePath() {
		return this.imagePath;
	}

	public Color color() {
		return color;
	}

	public Shape shape() {
		return shape;
	}
}
