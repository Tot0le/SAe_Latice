package latice.model.tile;

public enum Shape {
	Dolphin,
	Gecko,
	Flower,
	Turtle,
	Bird,
	Feather,
	None;
	
	public String getNameInPath() {
        return this.name().toLowerCase();
    }
}
