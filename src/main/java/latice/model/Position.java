package latice.model;

import java.util.Objects;

public class Position {
	Integer row;
	Integer column;

	public Position(Integer row, Integer column) {
		this.row = row;
		this.column = column;
	}
	
	public Integer row() {
		return row;
	}
	
	public Integer column() {
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
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Position) {
			Position position = (Position) obj;
			if (this.row.equals(position.row()) && this.column.equals(position.column())) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(this.row, this.column);
	}

	
	@Override
	public String toString() {
		return "[Pos : " + row + "," + column + "]";
	}
	
	
}
