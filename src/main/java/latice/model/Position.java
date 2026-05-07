package latice.model;

public class Position {
	Integer row;
	Integer column;

	public Position(Integer row, Integer column) {
		this.row = row;
		this.column = column;
	}
	
	public Integer getRow() {
		return row;
	}
	
	public Integer getColumn() {
		return column;
	}
	
//	public void IndexPositions() {
//		for(int i=1; i<10; i++) {
//			for(int j=1;j<10;j++) {
//				new Position(i, j);
//			}
//		}		
//	}
//	
//	public boolean PositionExists(Position coords) {
//		for(int i=1; i<10; i++) {
//			for(int j=1;j<10;j++) {
//				if(coords == Position(i, j)) return true;
//			}
//		}
//	}
	
	@Override
	public String toString() {
		return "[Pos : " + row + "," + column + "]";
	}
	
	
}
