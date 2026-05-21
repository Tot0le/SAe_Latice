package latice.util;

public enum ImagePath {
	BACKGROUND_SEA("/images/bg_sea.png"),
	BACKGROUND_MOON("/images/bg_moon.png"),
	BACKGROUND_SUN("/images/bg_sun.png"),
	
	// tiles
	GREEN_BIRD("/images/tiles/bird_g"),
	MAGENTA_BIRD("images/tiles/bird_m"),
	NAVY_BIRD("/images/tiles/bird_n"),
    RED_BIRD("/images/tiles/bird_r"),
    TEAL_BIRD("/images/tiles/bird_t"),
    YELLOW_BIRD("/images/tiles/bird_y"),
    
    GREEN_DOLPHIN("/images/tiles/dolphin_g"),
    MAGENTA_DOLPHIN("/images/tiles/dolphin_m"),
    NAVY_DOLPHIN("/images/tiles/dolphin_n"),
    RED_DOLPHIN("/images/tiles/dolphin_r"),
    TEAL_DOLPHIN("/images/tiles/dolphin_t"),
    YELLOW_DOLPHIN("/images/tiles/dolphin_y"),
    
    GREEN_FEATHER("/images/tiles/feather_g"),
    MAGENTA_FEATHER("/images/tiles/feather_m"),
    NAVY_FEATHER("/images/tiles/feather_n"),
    RED_FEATHER("/images/tiles/feather_r"),
    TEAL_FEATHER("/images/tiles/feather_t"),
    YELLOW_FEATHER("/images/tiles/feather_y"),
    
    GREEN_FLOWER("/images/tiles/flower_g"),
    MAGENTA_FLOWER("/images/tiles/flower_m"),
    NAVY_FLOWER("/images/tiles/flower_n"),
    RED_FLOWER("/images/tiles/flower_r"),
    TEAL_FLOWER("/images/tiles/flower_t"),
    YELLOW_FLOWER("/images/tiles/flower_y"),
    
    GREEN_GECKO("/images/tiles/gecko_g"),
    MAGENTA_GECKO("/images/tiles/gecko_m"),
    NAVY_GECKO("/images/tiles/gecko_n"),
    RED_GECKO("/images/tiles/gecko_r"),
    TEAL_GECKO("/images/tiles/gecko_t"),
    YELLOW_GECKO("/images/tiles/gecko_y"),
    
    GREEN_TURTLE("/images/tiles/turtle_g"),
    MAGENTA_TURTLE("/images/tiles/turtle_m"),
    NAVY_TURTLE("/images/tiles/turtle_n"),
    RED_TURTLE("/images/tiles/turtle_r"),
    TEAL_TURTLE("/images/tiles/turtle_t"),
    YELLOW_TURTLE("/images/tiles/turtle_y"),
    
	// Menu related image
	BACKGROUND_MENU("/images/latice_background.png");
	
	
	private String path;
	
	private	ImagePath(String path) {
		this.path = path;
	}
	
	public String path() {
		return this.path;
	}
}
