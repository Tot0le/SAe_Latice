package latice.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import latice.model.tile.Color;
import latice.model.tile.Shape;
import latice.model.tile.Tile;

public class Referee {
	private GameBoard gameboard;
	private ArrayList<Player> playerList;
	private Player currentPlayer;
	private int currentPlayerIndex = 0;
	private int cycleCount = 0;
	private boolean isTheGameEnd;
	private int priceNewAction;
	
	public Referee(GameBoard gameboard, ArrayList<Player> playerList) {
		this.gameboard = gameboard;
		this.currentPlayer = playerList.getFirst();
		this.setPlayerList(playerList);
		this.priceNewAction = 2;
	}
	
	public static void dealTheCards(Pool originPool, Pool poolPlayer1, Pool poolPlayer2) {
		Integer tilesNumber = originPool.tilesAmounts();

		for (int i = 0; i < tilesNumber; i = i + 2) {
			poolPlayer1.addTile(originPool.getTile(i));
			poolPlayer2.addTile(originPool.getTile(i + 1));
		}
	}

	public static void shuffle(Pool pool) {
		pool.shuffle();

	}
	
	public static List<Player> randomChoosePlayerOrder(List<Player> players) {
		ArrayList<Player> orderedListOfPlayer = new ArrayList<>(players); // ordered means by the play order of the player
		
		Integer orderNumber = 0;
		
		Collections.shuffle(orderedListOfPlayer);
		
		for (Player player: orderedListOfPlayer) {
			orderNumber += 1;
			player.setOrderNumber(orderNumber);
		}

		return orderedListOfPlayer;
		
	}

	public boolean checkIfMoveIsLegal(Integer selectedTileIndexRack, Position selectedTilePositionGmBrd ) {
		boolean isLegal = false;
		Tile selectedTile = currentPlayer.rack().getTile(selectedTileIndexRack);
		ArrayList<Tile> nearbyTiles = (ArrayList<Tile>) this.gameboard.getNearbyTiles(selectedTilePositionGmBrd);
		if (currentPlayer.isMoveAvailable()) {
			isLegal = true;
			
			if (gameboard.getTile(selectedTilePositionGmBrd) != null) {
				isLegal = false;
			} else if (nearbyTiles.isEmpty() && ! gameboard.isEmpty() || ! gameboard.isMoonAt(selectedTilePositionGmBrd) && gameboard.isEmpty() ){
				isLegal = false;
			} else {
				for (Tile tile : nearbyTiles) {
					if (tile.color() != selectedTile.color() && tile.shape() != selectedTile.shape()) {
						isLegal = false;
					}
				}
			}
		}
		
		if (isLegal) {
			currentPlayer.rack().popTile(selectedTileIndexRack);
			gameboard.put(selectedTilePositionGmBrd, selectedTile);
			currentPlayer.addPoints(Referee.calculatePoints(nearbyTiles, selectedTile, gameboard.isSunAt(selectedTilePositionGmBrd)));
			currentPlayer.setMoveAvailable(false);
		}
		return isLegal;
	}

	public static Integer calculatePoints(ArrayList<Tile> nearbyTiles, Tile tilePlaced, boolean isSunAt) {
		Integer numberOfTileMatches = 0;
		Integer points = 0;
		Color colorOfTilePlaced = tilePlaced.color();
		Shape shapeOfTilePlaced = tilePlaced.shape();
	
		for (Tile tile : nearbyTiles) {
			if (tile.color() == colorOfTilePlaced || tile.shape() == shapeOfTilePlaced) {
				numberOfTileMatches += 1;
			}
		}
		if (numberOfTileMatches == 2) {
			points = 1;
		}else if (numberOfTileMatches == 3) {
			points = 2;
		}else if (numberOfTileMatches == 4) {
			points = 4;
		}
		
		if (isSunAt) {
			points += 2;
		}
		
		return points;
	}
	
	public void nextTurn(GameBoard gameboard) {
		// the previous player has to draw one tile if their rack is not full
		if (!this.currentPlayer.rack().isFull()) {
			this.currentPlayer.drawMaxTile();
		}
		
		// if the game is not finished, 
		if (this.cycleCount < gameboard.nbCycle() && this.currentPlayer.totalTilesNumber() > 0) {
			
			// change current player
			this.currentPlayerIndex = this.currentPlayerIndex + 1;
			if (this.currentPlayerIndex >= this.playerList.size()) {
				this.cycleCount += 1;
				this.currentPlayerIndex = this.currentPlayerIndex % this.playerList.size();
			}
		
			// update the current player
			this.currentPlayer = playerList.get(this.currentPlayerIndex);
			
			// regive the player a free move
			this.currentPlayer.setMoveAvailable(true);
			this.isTheGameEnd = false;
		} else {
			this.isTheGameEnd = true;
		}
	}
	
	public Player endGame() {
		Player winner = null;
		Integer winnerTotalNumber = (int) Double.POSITIVE_INFINITY;
		boolean isDraw = false;
		for (Player player: this.playerList) {
			if (player.totalTilesNumber() < winnerTotalNumber) {
				winner = player;
				winnerTotalNumber = winner.totalTilesNumber();
				isDraw = false;
			} else if (player.totalTilesNumber() == (int) winnerTotalNumber) {
				isDraw = true;
			}
		}

		if (isDraw) {
			winner = null;
		}
		return winner;
	}

	public Player currentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int currentPlayerIndex() {
		return currentPlayerIndex;
	}

	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
	}

	public int cycleCount() {
		return cycleCount;
	}

	public void setCycleCount(int cycleCount) {
		this.cycleCount = cycleCount;
	}

	public ArrayList<Player> playerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public boolean isTheGameEnd() {
		return isTheGameEnd;
	}

	public void setTheGameEnd(boolean isTheGameEnd) {
		this.isTheGameEnd = isTheGameEnd;
	}

	public int priceNewAction() {
		return priceNewAction;
	}
}



