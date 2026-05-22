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
	public void exchangeTheRack() {
		//TODO tilesAmount -5 puis tiles Amount +5
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
	// getteurs :
	public Integer Points() {
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
