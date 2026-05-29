package latice.gui.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	private boolean currentPlayerFreeMoveAvailable;
	private LabelController lblController;
	/// V8 FEATURE DO NOT DELETE //////
//	private boolean multipleSelection;
//	private ArrayList<Integer> selectedTilesIndex;
//	private Button confirmButton;
	/// V8 FEATURE DO NOT DELETE //////

	public GameController(GameBoard gameboard, GameBoardIhm gameboardIhm, ArrayList<Player> playerList, GameScene gameScene, LabelController lblController) {
		this.gameboard = gameboard;
		this.gameboardIhm = gameboardIhm;
		this.gameScene = gameScene;
		this.currentPlayer = playerList.getFirst();
		this.currentPlayerFreeMoveAvailable = true;
		this.playerAndRackMap = new LinkedHashMap<>();
		
		this.lblController = lblController;

		this.lblController.currentPlayerLabel().setText(this.currentPlayer.username() + "'s turn");
		/// V8 FEATURE DO NOT DELETE //////
//		this.multipleSelection = false;
//		this.selectedTilesIndex = new ArrayList<Integer>();
		
		for (Player player: playerList) {
			RackIhm newRackIhm = new RackIhm(player.rack());
			newRackIhm.bindController(this);
			this.playerAndRackMap.put(player, newRackIhm);
			
		} 
		
		this.gameboardIhm.bindController(this);
	}
	
	public void endGame() {

		Player winner = null;
		Integer winnerTotalNumber = (int) Double.POSITIVE_INFINITY;
		boolean isDraw = false;
		for (Player player: this.playerAndRackMap.keySet()) {
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
		if (winner == null) {
			System.out.println("It's a draw !");
			this.lblController.endMessageLbl().setTextFill(Color.GRAY);
			this.lblController.endMessageLbl().setFont(Font.font("", FontWeight.BOLD, 30));
			this.lblController.endMessageLbl().setText("It's a draw !");
		} else {
			System.out.println("The winner is the player : " + winner.username());
			this.lblController.endMessageLbl().setTextFill(Color.GOLD);
			this.lblController.endMessageLbl().setFont(Font.font("", FontWeight.BOLD, 30));
			this.lblController.endMessageLbl().setText("The winner is the player : " + winner.username());
		}
	}

	public void nextTurn() {
		// the previous player has to draw one tile if their rack is not full
		if (!this.currentPlayer.rack().isFull()) {
			this.currentPlayer.drawMaxTile();
		}
		
		// if the game is not finished, 
		if (this.cycleCount < this.gameboard.nbCycle() && this.currentPlayer.totalTilesNumber() > 0) {
			
			// change current player
			this.currentPlayerIndex = this.currentPlayerIndex + 1;
			if (this.currentPlayerIndex >= this.playerAndRackMap.size()) {
				this.cycleCount += 1;
				this.currentPlayerIndex = this.currentPlayerIndex % this.playerAndRackMap.size();
			}
		
			// update the current player
			this.currentPlayer = new ArrayList<>(this.playerAndRackMap.keySet()).get(this.currentPlayerIndex);
			this.lblController.currentPlayerLabel().setText(this.currentPlayer.username() + "'s turn");
			
			// update ihm rack related content
			this.displayCurrentPlayerRack();
			this.getCurrentPlayerRackIhm().updateRackTiles();
			
			// regive the player a free move
			this.currentPlayerFreeMoveAvailable = true;
		} else {
			this.endGame();
		}
		
		
	}
	
	public void handleTileSelection(Integer indexRack) {
		System.out.println("Mouse clicked on rack index : " + indexRack);
		/// V8 FEATURE DO NOT DELETE //////
//		if (this.multipleSelection) {
//			this.selectedTilesIndex.add(indexRack);
//		} else {
		/// V8 FEATURE DO NOT DELETE //////
		if (currentPlayer.rack().getTile(indexRack) != null ) {
			this.selectedTileIndex = indexRack;
		} else {
			this.selectedTileIndex = null;
		}
		
	//	}
	}
	
	public void handleTilePlacement(Position gameboardPosition) {
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
	
	public void exchangeAllTilesBtnHandler() {
		if (this.currentPlayerFreeMoveAvailable) {
			this.currentPlayer.exchangeAllTheRack();
			System.out.println("exchange complete");
			this.getCurrentPlayerRackIhm().updateRackTiles();
			this.currentPlayerFreeMoveAvailable = false;
		}
	}
	////////////////////////////////////// V8 FEATURE DO NOT DELETE ////////////////////////////////////////
	////////////////////////////////////// V8 FEATURE DO NOT DELETE ////////////////////////////////////////
	////////////////////////////////////// V8 FEATURE DO NOT DELETE ////////////////////////////////////////
//	public void exchangeTilesBtnHandler() {
//		if (this.currentPlayerFreeMoveAvailable) {
//			if (this.multipleSelection) {
//				deselectMultipleBtn();
//				System.out.println("deselect multiple");
//			} else {
//				this.multipleSelection = true;
//				System.out.println("select multiple");
//			}
////			this.btnConfirm.show()
//		}
//	}
//	
//	public void confirmBtnHandler() {
//		if (this.multipleSelection) {
//			this.currentPlayer.exchangeTheRack(selectedTilesIndex);
//			System.out.println("exchange complete");
//			this.getCurrentPlayerRackIhm().updateRackTiles();
//			
//			deselectMultipleBtn();
//		}
//	}
//	
//	public void deselectMultipleBtn() {
//		this.multipleSelection = false;
//		this.selectedTilesIndex.clear();
//	}
	////////////////////////////////////// V8 FEATURE DO NOT DELETE ////////////////////////////////////////
	////////////////////////////////////// V8 FEATURE DO NOT DELETE ////////////////////////////////////////
	////////////////////////////////////// V8 FEATURE DO NOT DELETE ////////////////////////////////////////

	public RackIhm getCurrentPlayerRackIhm() {
		return this.playerAndRackMap.get(this.currentPlayer);
	}
	
	public void displayCurrentPlayerRack() {
		this.gameScene.rackEmplacement().getChildren().clear();
		this.gameScene.rackEmplacement().getChildren().add(this.getCurrentPlayerRackIhm());
	}
	
	public void updateScoreLabel() {
		this.lblController.scorePlayerLabels().get(currentPlayerIndex).setText("Score player " + (this.currentPlayerIndex + 1) + " : " + this.currentPlayer.points());
	}
	
}
