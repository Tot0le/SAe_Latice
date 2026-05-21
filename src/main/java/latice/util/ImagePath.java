package latice.util;

public enum ImagePath {
	BACKGROUND_SEA("/images/bg_sea.png"),
	BACKGROUND_MOON("/images/bg_moon.png"),
	BACKGROUND_SUN("/images/bg_sun.png"),
	
	// tiles
	GREEN_BIRD("/images/tiles/bird_g.png"),
	MAGENTA_BIRD("images/tiles/bird_m.png"),
	NAVY_BIRD("/images/tiles/bird_n.png"),
    RED_BIRD("/images/tiles/bird_r.png"),
    TEAL_BIRD("/images/tiles/bird_t.png"),
    YELLOW_BIRD("/images/tiles/bird_y.png"),
    
    GREEN_DOLPHIN("/images/tiles/dolphin_g.png"),
    MAGENTA_DOLPHIN("/images/tiles/dolphin_m.png"),
    NAVY_DOLPHIN("/images/tiles/dolphin_n.png"),
    RED_DOLPHIN("/images/tiles/dolphin_r.png"),
    TEAL_DOLPHIN("/images/tiles/dolphin_t.png"),
    YELLOW_DOLPHIN("/images/tiles/dolphin_y.png"),
    
    GREEN_FEATHER("/images/tiles/feather_g.png"),
    MAGENTA_FEATHER("/images/tiles/feather_m.png"),
    NAVY_FEATHER("/images/tiles/feather_n.png"),
    RED_FEATHER("/images/tiles/feather_r.png"),
    TEAL_FEATHER("/images/tiles/feather_t.png"),
    YELLOW_FEATHER("/images/tiles/feather_y.png"),
    
    GREEN_FLOWER("/images/tiles/flower_g.png"),
    MAGENTA_FLOWER("/images/tiles/flower_m.png"),
    NAVY_FLOWER("/images/tiles/flower_n.png"),
    RED_FLOWER("/images/tiles/flower_r.png"),
    TEAL_FLOWER("/images/tiles/flower_t.png"),
    YELLOW_FLOWER("/images/tiles/flower_y.png"),
    
    GREEN_GECKO("/images/tiles/gecko_g.png"),
    MAGENTA_GECKO("/images/tiles/gecko_m.png"),
    NAVY_GECKO("/images/tiles/gecko_n.png"),
    RED_GECKO("/images/tiles/gecko_r.png"),
    TEAL_GECKO("/images/tiles/gecko_t.png"),
    YELLOW_GECKO("/images/tiles/gecko_y.png"),
    
    GREEN_TURTLE("/images/tiles/turtle_g.png"),
    MAGENTA_TURTLE("/images/tiles/turtle_m.png"),
    NAVY_TURTLE("/images/tiles/turtle_n.png"),
    RED_TURTLE("/images/tiles/turtle_r.png"),
    TEAL_TURTLE("/images/tiles/turtle_t.png"),
    YELLOW_TURTLE("/images/tiles/turtle_y.png"),
    
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
