package latice.util;

import latice.model.tile.Color;
import latice.model.tile.Shape;

public enum ImagePath {
	BACKGROUND_SEA("/images/bg_sea.png", Color.None, Shape.None),
	BACKGROUND_MOON("/images/bg_moon.png", Color.None, Shape.None),
	BACKGROUND_SUN("/images/bg_sun.png", Color.None, Shape.None),

	// tiles
	GREEN_BIRD("/images/tiles/bird_g.png", Color.Green, Shape.Bird),
    MAGENTA_BIRD("/images/tiles/bird_m.png", Color.Magenta, Shape.Bird),
    NAVY_BIRD("/images/tiles/bird_n.png", Color.Navy, Shape.Bird),
    RED_BIRD("/images/tiles/bird_r.png", Color.Red, Shape.Bird),
    TEAL_BIRD("/images/tiles/bird_t.png", Color.Teal, Shape.Bird),
    YELLOW_BIRD("/images/tiles/bird_y.png", Color.Yellow, Shape.Bird),

    GREEN_DOLPHIN("/images/tiles/dolphin_g.png", Color.Green, Shape.Dolphin),
    MAGENTA_DOLPHIN("/images/tiles/dolphin_m.png", Color.Magenta, Shape.Dolphin),
    NAVY_DOLPHIN("/images/tiles/dolphin_n.png", Color.Navy, Shape.Dolphin),
    RED_DOLPHIN("/images/tiles/dolphin_r.png", Color.Red, Shape.Dolphin),
    TEAL_DOLPHIN("/images/tiles/dolphin_t.png", Color.Teal, Shape.Dolphin),
    YELLOW_DOLPHIN("/images/tiles/dolphin_y.png", Color.Yellow, Shape.Dolphin),

    GREEN_FEATHER("/images/tiles/feather_g.png", Color.Green, Shape.Feather),
    MAGENTA_FEATHER("/images/tiles/feather_m.png", Color.Magenta, Shape.Feather),
    NAVY_FEATHER("/images/tiles/feather_n.png", Color.Navy, Shape.Feather),
    RED_FEATHER("/images/tiles/feather_r.png", Color.Red, Shape.Feather),
    TEAL_FEATHER("/images/tiles/feather_t.png", Color.Teal, Shape.Feather),
    YELLOW_FEATHER("/images/tiles/feather_y.png", Color.Yellow, Shape.Feather),

    GREEN_FLOWER("/images/tiles/flower_g.png", Color.Green, Shape.Flower),
    MAGENTA_FLOWER("/images/tiles/flower_m.png", Color.Magenta, Shape.Flower),
    NAVY_FLOWER("/images/tiles/flower_n.png", Color.Navy, Shape.Flower),
    RED_FLOWER("/images/tiles/flower_r.png", Color.Red, Shape.Flower),
    TEAL_FLOWER("/images/tiles/flower_t.png", Color.Teal, Shape.Flower),
    YELLOW_FLOWER("/images/tiles/flower_y.png", Color.Yellow, Shape.Flower),

    GREEN_GECKO("/images/tiles/gecko_g.png", Color.Green, Shape.Gecko),
    MAGENTA_GECKO("/images/tiles/gecko_m.png", Color.Magenta, Shape.Gecko),
    NAVY_GECKO("/images/tiles/gecko_n.png", Color.Navy, Shape.Gecko),
    RED_GECKO("/images/tiles/gecko_r.png", Color.Red, Shape.Gecko),
    TEAL_GECKO("/images/tiles/gecko_t.png", Color.Teal, Shape.Gecko),
    YELLOW_GECKO("/images/tiles/gecko_y.png", Color.Yellow, Shape.Gecko),

    GREEN_TURTLE("/images/tiles/turtle_g.png", Color.Green, Shape.Turtle),
    MAGENTA_TURTLE("/images/tiles/turtle_m.png", Color.Magenta, Shape.Turtle),
    NAVY_TURTLE("/images/tiles/turtle_n.png", Color.Navy, Shape.Turtle),
    RED_TURTLE("/images/tiles/turtle_r.png", Color.Red, Shape.Turtle),
    TEAL_TURTLE("/images/tiles/turtle_t.png", Color.Teal, Shape.Turtle),
    YELLOW_TURTLE("/images/tiles/turtle_y.png", Color.Yellow, Shape.Turtle),

	// Menu related image
	BACKGROUND_MENU("/images/latice_background.png", Color.None, Shape.None),
	QUIT_BUTTON("/images/quit_button.png", Color.None, Shape.None),
	PLAY_BUTTON("/images/play_button.png", Color.None, Shape.None),
	RULES_BUTTON("/images/rules_button.png", Color.None, Shape.None),
	RULES_BACKGROUND("/images/rules.png", Color.None, Shape.None);


	private String imagePath;
	private Color color;
	private Shape shape;

	private	ImagePath(String path, Color color, Shape shape) {
		this.imagePath = path;
		this.color = color;
		this.shape = shape;
	}

	public static ImagePath getImagePath(Shape shape, Color color) {
		ImagePath returnedImagePath = null;
		for (ImagePath currentImgPath : ImagePath.values()) {
			if (currentImgPath.shape() == shape && currentImgPath.color() == color) {
				returnedImagePath = currentImgPath;
				return returnedImagePath;
			}
		}
		return returnedImagePath;
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
