package latice.util;

public enum ImagePath {
	BACKGROUND_SEA("/images/bg_sea.png");
	
	
	private String path;
	
	private	ImagePath(String path) {
		this.path = path;
	}
	
	public String path() {
		return this.path;
	}
}
