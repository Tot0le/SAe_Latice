package latice.util;

public enum ImagePath {
	BACKGROUND_SEA("/images/bg_sea.png"),
	BACKGROUND_MOON("/images/bg_moon.png"),
	BACKGROUND_SUN("/images/bg_sun.png");
	
	
	private String path;
	
	private	ImagePath(String path) {
		this.path = path;
	}
	
	public String path() {
		return this.path;
	}
}
