package latice.model.tile;

public enum Color {
	Yellow,
	Navy,
	Magenta,
	Red,
	Green,
	Teal,
	None;
	
	public String getNameInPath() {
        return this.name().substring(0, 1).toLowerCase();
    }
}
