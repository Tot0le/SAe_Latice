package latice.gui.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javafx.scene.control.Button;
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
	private List<Label> scorePlayerLabels; //TODO fix it because player 1 should be the first to play
	private boolean currentPlayerFreeMoveAvailable; //TODO fix it because player 1 should be the first to play
	private boolean multipleSelection;
	private ArrayList<Integer> selectedTilesIndex;
	private Button confirmButton;
	 
	public GameController(GameBoard gameboard, GameBoardIhm gameboardIhm, ArrayList<Player> playerList, GameScene gameScene, List<Label> scorePlayerLabels) {
		this.gameboard = gameboard;
		this.gameboardIhm = gameboardIhm;
		this.gameScene = gameScene;
		this.currentPlayer = playerList.getFirst();
		this.playerAndRackMap = new LinkedHashMap<>();
		this.scorePlayerLabels = scorePlayerLabels;
		this.currentPlayerFreeMoveAvailable = true;
		this.multipleSelection = false;
		this.selectedTilesIndex = new ArrayList<Integer>();
		
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
		Player winner = null;
		Integer winnerTotalNumber = (int) Double.POSITIVE_INFINITY;
		for (Player player: this.playerAndRackMap.keySet()) {
			if (player.totalTilesNumber() < winnerTotalNumber) {
				winner = player;
				winnerTotalNumber = winner.totalTilesNumber();
			}
		}
		System.out.println("Le gagnant est " + winner.orderNumber());
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
		if (this.multipleSelection) {
			this.selectedTilesIndex.add(indexRack);
		} else {
			if (currentPlayer.rack().getTile(indexRack) != null ) {
				this.selectedTileIndex = indexRack;
			} else {
				this.selectedTileIndex = null;
			}
		}
		
	}
	
	public void handleTilePlacement(Position gameboardPosition) {
		//System.out.println("Mouse clicked on board position : " + gameboardPosition);
		if (selectedTileIndex != null) {
			if (this.currentPlayerFreeMoveAvailable) {
				Tile selectedTile = this.currentPlayer.rack().getTile(this.selectedTileIndex);
				ArrayList<Tile> nearbyTiles = (ArrayList<Tile>) this.gameboard.getNearbyTiles(gameboardPosition);
				
				boolean isLegal = Referee.checkIfMoveIsLegal(nearbyTiles, gameboard, gameboardPosition, selectedTile);
				
				if (isLegal) {
					currentPlayer.rack().popTile(selectedTileIndex);
					gameboard.put(gameboardPosition, selectedTile);
					this.currentPlayer.addPoints(Referee.calculatePoints(nearbyTiles, selectedTile));
					System.out.println(currentPlayerIndex);
					updateScoreLabel();
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
    }
	
	public void exchangeTilesBtnHandler() {
		if (this.multipleSelection) {
			deselectMultipleBtn();
			System.out.println("deselect multiple");
		} else {
			this.multipleSelection = true;
			System.out.println("select multiple");
		}
//		this.btnConfirm.show()
	}
	
	public void confirmBtnHandler() {
		if (this.multipleSelection) {
			this.currentPlayer.exchangeTheRack(selectedTilesIndex);
			System.out.println("exchange complete");
			this.getCurrentPlayerRackIhm().updateRackTiles();
			
			deselectMultipleBtn();
		}
	}
	
	public void deselectMultipleBtn() {
		this.multipleSelection = false;
		this.selectedTilesIndex.clear();
	}
	
//	public void selectMultiplesTilesIndex(){
//		ArrayList<Integer> selectedTilesIndex = new ArrayList<>();
//		
//		
//		return selectedTilesIndex;
//	}
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
	
	public void updateScoreLabel() {
		scorePlayerLabels.get(currentPlayerIndex).setText("Score player " + (this.currentPlayerIndex + 1) + " : " + this.currentPlayer.points());
	}
	
}
