package latice.model;

import java.util.ArrayList;
import java.util.List;
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
	
	public List<Position> getNearbyPositions(){
		ArrayList<Position> nearbyPositions= new ArrayList<>();
		
		nearbyPositions.add(new Position(row + 1, column));
		nearbyPositions.add(new Position(row - 1, column));
		nearbyPositions.add(new Position(row, column + 1));
		nearbyPositions.add(new Position(row, column - 1));
		 
		return nearbyPositions;
    }


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
