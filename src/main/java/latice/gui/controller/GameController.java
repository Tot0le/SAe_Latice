package latice.gui.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javafx.scene.control.Label;
import latice.gui.model.GameBoardIhm;
import latice.gui.model.RackIhm;
import latice.gui.view.GameScene;
import latice.model.GameBoard;
import latice.model.Player;
import latice.model.Position;
import latice.model.Referee;
import latice.model.tile.Tile;

public class GameController {
	private final GameBoard gameboard;
	private final GameBoardIhm gameboardIhm;
	private LinkedHashMap<Player, RackIhm> playerAndRackMap;
	private Player currentPlayer;
	private int currentPlayerIndex = 0;
	private int cycleCount = 0;
	private Integer selectedTileIndex = null;
	private GameScene gameScene;
	private List<Label> scorePlayerLabels;
	private boolean currentPlayerFreeMoveAvailable;
	 
	public GameController(GameBoard gameboard, GameBoardIhm gameboardIhm, ArrayList<Player> playerList, GameScene gameScene, List<Label> scorePlayerLabels) {
		this.gameboard = gameboard;
		this.gameboardIhm = gameboardIhm;
		this.gameScene = gameScene;
		this.currentPlayer = playerList.getFirst();
		this.playerAndRackMap = new LinkedHashMap<>();
		this.scorePlayerLabels = scorePlayerLabels;
		this.currentPlayerFreeMoveAvailable = true;
		
		for (Player player: playerList) {
			RackIhm newRackIhm = new RackIhm(player.rack());
			newRackIhm.bindController(this);
			this.playerAndRackMap.put(player, newRackIhm);
			
		} 
		
		this.gameboardIhm.bindController(this);
	}
	
	public void endGame() {
		System.out.println("GG, you finished the game");
		//TODO endgame
	}
	public void nextRound() {
		//TODO
	}
	
	public void nextTurn() {
		
		this.currentPlayerIndex = this.currentPlayerIndex + 1;
		if (this.currentPlayerIndex >= this.playerAndRackMap.size()) {
			this.cycleCount += 1;
			this.currentPlayerIndex = this.currentPlayerIndex % this.playerAndRackMap.size();
		}
		
		if (this.cycleCount < this.gameboard.nbCycle()) {
			this.currentPlayer = new ArrayList<>(this.playerAndRackMap.keySet()).get(this.currentPlayerIndex);
			this.displayCurrentPlayerRack();
			this.getCurrentPlayerRackIhm().updateRackTiles();
			this.currentPlayerFreeMoveAvailable = true;
		} else {
			this.endGame();
		}
		
		
	}
	
	public void playerChoices(Player player) {
		
	}
	
	public void handleTileSelection(Integer indexRack) {
		System.out.println("Mouse clicked on rack index : " + indexRack);

		if (currentPlayer.rack().getTile(indexRack) != null ) {
			this.selectedTileIndex = indexRack;
		} else {
			this.selectedTileIndex = null;
		}
	}
	
	public void handleTilePlacement(Position gameboardPosition) {
		//System.out.println("Mouse clicked on board position : " + gameboardPosition);
		// TODO determine end of a turn
		if (selectedTileIndex != null) {
			if (this.currentPlayerFreeMoveAvailable) {
				Tile selectedTile = this.currentPlayer.rack().getTile(this.selectedTileIndex);
				ArrayList<Tile> nearbyTiles = (ArrayList<Tile>) this.gameboard.getNearbyTiles(gameboardPosition);
				
				boolean isLegal = Referee.checkIfMoveIsLegal(nearbyTiles, gameboard, gameboardPosition, selectedTile);
				
				if (isLegal) {
					currentPlayer.rack().popTile(selectedTileIndex);
					gameboard.put(gameboardPosition, selectedTile);
					this.currentPlayer.addPoints(Referee.calculatePoints(nearbyTiles, selectedTile));
					
					scorePlayerLabels.get(currentPlayerIndex).setText("Score player " + this.currentPlayerIndex + " : " + this.currentPlayer.points());
					gameboardIhm.update();
					getCurrentPlayerRackIhm().updateRackTiles();
					selectedTileIndex = null;
					this.currentPlayerFreeMoveAvailable = false;
				}
			
			}
			
		}
	}

	public void endTurnBtnHandler() {        
    	nextTurn();
    	System.out.println("handle");
    }
	
	public void exchangeTiles() {
		// TODO
	}
	
	public void pass() {
		// TODO
	}
	
	public RackIhm getCurrentPlayerRackIhm() {
		return this.playerAndRackMap.get(this.currentPlayer);
	}
	
	public void displayCurrentPlayerRack() {
		this.gameScene.rackEmplacement().getChildren().clear();
		this.gameScene.rackEmplacement().getChildren().add(this.getCurrentPlayerRackIhm());
	}
	
	
}
