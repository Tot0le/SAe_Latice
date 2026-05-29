package latice.model;

import java.util.ArrayList;

import latice.gui.Console;
import latice.model.tile.Tile;
import latice.util.ShouldNotBePossibleException;

public class Player {
	private String username;
	private Integer points;
	private Rack rack;
	private Pool pool;
	private GameBoard gameboard;
	private Integer orderNumber;
	private boolean moveAvailable;

	public Player(String username, Integer points, Rack rack, Pool pool, GameBoard gameboard) {
		this.username = username;
		this.points = points;
		this.rack = rack;
		this.pool = pool;
		this.gameboard = gameboard;
		this.orderNumber = null;
		this.setMoveAvailable(true);
	}
	
	public Player(Integer points, Rack rack, Pool pool, GameBoard gameboard) {
		this.points = points;
		this.rack = rack;
		this.pool = pool;
		this.gameboard = gameboard;
		this.orderNumber = null;
	}

	public void buyANewAction() throws ShouldNotBePossibleException {
		if (moveAvailable) {
			throw new ShouldNotBePossibleException("The user should not be able to click on the button \"buy a new action\" if a move is already available.");
		} else {
			this.points = this.points - 2;
			this.moveAvailable = true;
		}
	}
	
	public void exchangeAllTheRack() {
		ArrayList<Integer> indexesOfTiles = new ArrayList<>();
		for (int index = 0; index < this.rack.maxTiles()-1;index++) indexesOfTiles.add(index);
		ArrayList<Tile> tilesToBeExchanged;
		
		tilesToBeExchanged = this.rack.takeTilesFromRack(indexesOfTiles);
		
		for (int i = 0; i < indexesOfTiles.size(); i++) {
			drawATile();
		}
		
		for (int i = 0; i < tilesToBeExchanged.size(); i++) {
			pool.addTile(tilesToBeExchanged.get(i));
		}
		
		pool.shuffle();
	}
	
	public boolean placeATile(Integer rackIndex, Position placementPosition) {
		Tile tile = rack.popTile(rackIndex);
		return gameboard.put(placementPosition, tile);
	}

	public void drawATile() {
		ArrayList<Tile> poolTiles = pool.getTiles();
		if (rack.getTiles().size() < rack.maxTiles()) {
			if (!poolTiles.isEmpty()) {
				Tile tileDrawn = poolTiles.getFirst();
				poolTiles.removeFirst();

				pool.setTiles(poolTiles);
				rack.addTile(tileDrawn);
			} else {
				Console.message("Impossible de piocher, Pool vide.");
			}
		} else {
			Console.message("Impossible de piocher, nombre maximum de tuile dans le Rack.");
		}
	}

	public void drawMaxTile() {
		while (rack.getTiles().size() < rack.maxTiles() && pool.size() > 0) {
			drawATile();
		}
	}
	
	public void addPoints(Integer addPoints) {
		this.points += addPoints;
	}
	
	// getteurs :
	public Integer points() {
		return this.points;
	}
	
	public Rack rack() {
		return this.rack;
	}
	
	public Pool pool() {
		return this.pool;
	}
	
	public Integer totalTilesNumber() {
		return this.pool.size() + this.rack.size();
	}
	
	public Integer orderNumber() {
		return this.orderNumber;
	}
	
	public String username() {
		return this.username;
	}
	
	public boolean isMoveAvailable() {
		return moveAvailable;
	}
	
	// setteurs :
	public void player(Integer points) {
		this.points = points;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setMoveAvailable(boolean moveAvailable) {
		this.moveAvailable = moveAvailable;
	}
}
