package latice.gui.controller;

import latice.gui.Console;
import latice.gui.model.GameBoardIhm;
import latice.gui.model.RackIhm;
import latice.gui.view.GameOutcomeLayout;
import latice.gui.view.GameScene;
import latice.model.GameBoard;
import latice.model.Player;
import latice.model.Position;
import latice.model.Referee;

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
			this.lblController.currentPlayerLabel().setText(referee.currentPlayer().username() + "'s turn");
			
			// update ihm related content
			this.rackIhm.updateRackTiles(referee.currentPlayer());
			this.gameBtnController.updateExchangeAllBtn();
			this.gameBtnController.updateBuyAnActionBtn();
		}
	}
	
	public void handleTileSelection(Integer indexRack) {
		Console.message("Mouse clicked on rack index : " + indexRack);
		
		if (referee.currentPlayer().rack().getTile(indexRack) != null ) {
			this.selectedTileIndex = indexRack;
		} else {
			this.selectedTileIndex = null;
		}
		
	//	}
	}
	
	public void handleTilePlacement(Position gameboardPosition) {
		if (selectedTileIndex != null) {
			boolean isLegal = referee.checkIfMoveIsLegal(selectedTileIndex, gameboardPosition);
			if (isLegal) {
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
	}
	
	public void buyANewActionBtnHandler() {
		gameBtnController.buyANewActionBtnHandler(lblController);
	}

}
