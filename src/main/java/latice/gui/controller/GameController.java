package latice.gui.controller;

import java.util.ArrayList;

import latice.gui.model.GameBoardIhm;
import latice.gui.model.RackIhm;
import latice.gui.view.GameOutcomeLayout;
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
	private MenuBtnController menuBtnController;
	private GameBtnController gameBtnController;

	public GameController(GameBoard gameboard, GameBoardIhm gameboardIhm, Referee referee, GameScene gameScene, LabelController lblController, MenuBtnController menuBtnController, GameBtnController gameBtnController) {
		this.gameboard = gameboard;
		this.gameboardIhm = gameboardIhm;
		this.gameScene = gameScene;
		this.referee = referee;
		this.rackIhm = new RackIhm();
		rackIhm.bindController(this);
		this.gameScene.rackEmplacement().getChildren().add(this.rackIhm);
		this.rackIhm.updateRackTiles(referee.currentPlayer());
		
		this.lblController = lblController;
		this.menuBtnController = menuBtnController;
		this.gameBtnController = gameBtnController;

		this.lblController.currentPlayerLabel().setText(this.referee.currentPlayer().username() + "'s turn");
		
		this.gameboardIhm.bindController(this);
	}
	
	public void endGame() {
		Player winner = referee.endGame();
		gameScene.lockBoardRackAndBtns(false);
		
		GameOutcomeLayout gameOutcomeLayout = new GameOutcomeLayout(winner, menuBtnController, this.gameScene.gameLayout());
		this.gameScene.setOverlayLayout(gameOutcomeLayout);
	}

	public void nextTurn() {
		referee.nextTurn(gameboard);
		if (referee.isTheGameEnd()) {
			this.endGame();
		} else {
			lblController.updateCurrentTurn();
			lblController.updatePoolNumber();
			this.lblController.currentPlayerLabel().setText(referee.currentPlayer().username() + "'s turn");
			
			// update ihm related content
			this.rackIhm.updateRackTiles(referee.currentPlayer());
			this.gameBtnController.updateExchangeAllBtn();
			this.gameBtnController.updateBuyAnActionBtn();
		}
	}
	
	public void handleTileSelection(Integer indexRack) {
		this.rackIhm.clearHighlights();
		
		if (referee.currentPlayer().rack().getTile(indexRack) != null ) {
			this.selectedTileIndex = indexRack;
			this.rackIhm.highlightTile(selectedTileIndex);
		} else {
			this.selectedTileIndex = null;
		}
	}
	
	public void handleTilePlacement(Position gameboardPosition) {
		if (selectedTileIndex != null) {
			Player currentPlayer = referee.currentPlayer();
			ArrayList<Tile> nearbyTiles = (ArrayList<Tile>) this.gameboard.getNearbyTiles(gameboardPosition);
			Tile selectedTile = currentPlayer.rack().getTile(selectedTileIndex);
			boolean isLegal = referee.checkIfMoveIsLegal(gameboardPosition, nearbyTiles, selectedTile);
			if (isLegal) {
				// place tile
				currentPlayer.rack().popTile(selectedTileIndex);
				gameboard.put(gameboardPosition, selectedTile);
				currentPlayer.addPoints(Referee.calculatePoints(nearbyTiles, selectedTile, gameboard.isSunAt(gameboardPosition)));
				currentPlayer.setMoveAvailable(false);
			
				selectedTileIndex = null;
				// IHM related :
				lblController.updateScoreLabel();
				gameboardIhm.update();
				this.rackIhm.updateRackTiles(referee.currentPlayer());
				this.gameBtnController.updateExchangeAllBtn();
				this.gameBtnController.updateBuyAnActionBtn();
			}
		}
	}

	public void endTurnBtnHandler() {        
    	nextTurn();
    }
	
	public void exchangeAllTilesBtnHandler() {
		gameBtnController.exchangeAllTilesBtnHandler(rackIhm);
		nextTurn();
	}
	
	public void buyANewActionBtnHandler() {
		gameBtnController.buyANewActionBtnHandler(lblController);
	}

}
