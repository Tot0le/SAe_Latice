package latice.model;

import java.util.ArrayList;

import latice.gui.Console;
import latice.model.tile.Tile;

public class Player {

	private Integer points;
	private Rack rack;
	private Pool pool;
	private GameBoard gameboard;
	private Integer orderNumber;

	public Player(Integer points, Rack rack, Pool pool, GameBoard gameboard) {
		this.points = points;
		this.rack = rack;
		this.pool = pool;
		this.gameboard = gameboard;
		this.orderNumber = null;
	}

	public void buyANewAction(Integer points) {
		points = points - 2;
		// TODO  Implémenter une méthode permettant de rajouter une action au joueur
	}
	public void exchangeTheRack(ArrayList<Integer> indexesOfTiles) {
		
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
		while (rack.getTiles().size() < rack.maxTiles()) {
			drawATile();
		}
	}

	public void passTheTurn() {
		//TODO Faire un compteur total pour le tour du nombre de joueurs et quand celui-ci = nb de joueur alors cycles = +1
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
	
	// setteurs :
	public void player(Integer points) {
		this.points = points;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
}
