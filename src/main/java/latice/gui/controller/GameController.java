package latice.gui.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
	private int roundCount = 0;
	private Integer selectedTileIndex = null;
	private GameScene gameScene;
	 
	public GameController(GameBoard gameboard, GameBoardIhm gameboardIhm, ArrayList<Player> playerList, GameScene gameScene) {
		this.gameboard = gameboard;
		this.gameboardIhm = gameboardIhm;
		this.gameScene = gameScene;
		this.currentPlayer = playerList.getFirst();
		this.playerAndRackMap = new LinkedHashMap<>();
		
		for (Player player: playerList) {
			RackIhm newRackIhm = new RackIhm(player.rack());
			newRackIhm.bindController(this);
			this.playerAndRackMap.put(player, newRackIhm);
			
		} 
		
		this.gameboardIhm.bindController(this);
	}
	
	public void nextRound() {
		
	}
	
	public void nextTurn() {
		
		this.currentPlayerIndex = this.currentPlayerIndex + 1;
		if (this.currentPlayerIndex >= this.playerAndRackMap.size()) {
			this.roundCount += 1;
			this.currentPlayerIndex = this.currentPlayerIndex % this.playerAndRackMap.size();
		}
		// TODO check the roundCount number
		
		this.currentPlayer = new ArrayList<>(this.playerAndRackMap.keySet()).get(this.currentPlayerIndex);
		this.displayCurrentPlayerRack();
		this.getCurrentPlayerRackIhm().updateRackTiles();
		
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
		if (selectedTileIndex != null) {
			Tile selectedTile = this.currentPlayer.rack().getTile(this.selectedTileIndex);
			ArrayList<Tile> nearbyTiles = this.gameboard.getNearbyTiles(gameboardPosition);
			
			boolean isLegal = Referee.checkIfMoveIsLegal(nearbyTiles, gameboard, gameboardPosition, selectedTile);
			
			if (isLegal) {
				currentPlayer.rack().popTile(selectedTileIndex);
				gameboard.put(gameboardPosition, selectedTile);
				gameboardIhm.update();
				getCurrentPlayerRackIhm().updateRackTiles();
				selectedTileIndex = null;
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
