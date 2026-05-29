package latice.gui.controller;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import latice.gui.Console;
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
	private RackIhm rackIhm;
	private final Referee referee;

	
	private Integer selectedTileIndex = null;
	private GameScene gameScene;
	private LabelController lblController;
	/// V8 FEATURE DO NOT DELETE //////
//	private boolean multipleSelection;
//	private ArrayList<Integer> selectedTilesIndex;
//	private Button confirmButton;
	/// V8 FEATURE DO NOT DELETE //////

	public GameController(GameBoard gameboard, GameBoardIhm gameboardIhm, Referee referee, GameScene gameScene, LabelController lblController) {
		this.gameboard = gameboard;
		this.gameboardIhm = gameboardIhm;
		this.gameScene = gameScene;
		this.referee = referee;
		this.rackIhm = new RackIhm();
		rackIhm.bindController(this);
		this.gameScene.rackEmplacement().getChildren().add(this.rackIhm);
		this.rackIhm.updateRackTiles(referee.currentPlayer());
		
		this.lblController = lblController;

		this.lblController.currentPlayerLabel().setText(this.referee.currentPlayer().username() + "'s turn");
		/// V8 FEATURE DO NOT DELETE //////
//		this.multipleSelection = false;
//		this.selectedTilesIndex = new ArrayList<Integer>();
		
		this.gameboardIhm.bindController(this);
	}
	
	public void endGame() {
		Player winner = referee.endGame();
		
		if (winner == null) {
			Console.message("It's a draw !");
			this.lblController.endMessageLbl().setTextFill(Color.GRAY);
			this.lblController.endMessageLbl().setFont(Font.font("", FontWeight.BOLD, 30));
			this.lblController.endMessageLbl().setText("It's a draw !");
		} else {
			Console.message("The winner is the player : " + winner.username());
			this.lblController.endMessageLbl().setTextFill(Color.GOLD);
			this.lblController.endMessageLbl().setFont(Font.font("", FontWeight.BOLD, 30));
			this.lblController.endMessageLbl().setText("The winner is the player : " + winner.username());
		}
	}

	public void nextTurn() {
		referee.nextTurn(gameboard);
		if (referee.isTheGameEnd()) {
			this.endGame();
		} else {
			this.lblController.currentPlayerLabel().setText(referee.currentPlayer().username() + "'s turn");
			
			// update ihm rack related content
			this.rackIhm.updateRackTiles(referee.currentPlayer());
		}
	}
	
	public void handleTileSelection(Integer indexRack) {
		Console.message("Mouse clicked on rack index : " + indexRack);
		/// V8 FEATURE DO NOT DELETE //////
//		if (this.multipleSelection) {
//			this.selectedTilesIndex.add(indexRack);
//		} else {
		/// V8 FEATURE DO NOT DELETE //////
		if (referee.currentPlayer().rack().getTile(indexRack) != null ) {
			this.selectedTileIndex = indexRack;
		} else {
			this.selectedTileIndex = null;
		}
		
	//	}
	}
	
	public void handleTilePlacement(Position gameboardPosition) {
		if (selectedTileIndex != null) {
			Player currentPlayer = referee.currentPlayer();
			if (currentPlayer.isMoveAvailable()) {
				Tile selectedTile = currentPlayer.rack().getTile(this.selectedTileIndex);
				ArrayList<Tile> nearbyTiles = (ArrayList<Tile>) this.gameboard.getNearbyTiles(gameboardPosition);
				
				boolean isLegal = Referee.checkIfMoveIsLegal(nearbyTiles, gameboard, gameboardPosition, selectedTile);
				
				if (isLegal) {
					currentPlayer.rack().popTile(selectedTileIndex);
					gameboard.put(gameboardPosition, selectedTile);
					currentPlayer.addPoints(Referee.calculatePoints(nearbyTiles, selectedTile));
					updateScoreLabel();
					gameboardIhm.update();
					this.rackIhm.updateRackTiles(currentPlayer);
					selectedTileIndex = null;
					currentPlayer.setMoveAvailable(false);
				}
			
			}
			
		}
	}

	public void endTurnBtnHandler() {        
    	nextTurn();
    }
	
	public void exchangeAllTilesBtnHandler() {
		Player currentPlayer = referee.currentPlayer();
		if (currentPlayer.isMoveAvailable()) {
			currentPlayer.exchangeAllTheRack();
			System.out.println("exchange complete");
			this.rackIhm.updateRackTiles(currentPlayer);
			currentPlayer.setMoveAvailable(false);
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

	public void updateScoreLabel() {
		int currentPlayerIndex = referee.currentPlayerIndex();
		this.lblController.scorePlayerLabels().get(currentPlayerIndex).setText("Score player " + (currentPlayerIndex + 1) + " : " + referee.currentPlayer().points());
	}
	
}
