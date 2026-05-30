package latice.gui.controller;

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
import latice.util.ShouldNotBePossibleException;

public class GameController {
	private final GameBoard gameboard;
	private final GameBoardIhm gameboardIhm;
	private RackIhm rackIhm;
	private final Referee referee;

	
	private Integer selectedTileIndex = null;
	private GameScene gameScene;
	private LabelController lblController;
	private GameBtnController gameBtnController;

	public GameController(GameBoard gameboard, GameBoardIhm gameboardIhm, Referee referee, GameScene gameScene, LabelController lblController, GameBtnController gameBtnController) {
		this.gameboard = gameboard;
		this.gameboardIhm = gameboardIhm;
		this.gameScene = gameScene;
		this.referee = referee;
		this.rackIhm = new RackIhm();
		rackIhm.bindController(this);
		this.gameScene.rackEmplacement().getChildren().add(this.rackIhm);
		this.rackIhm.updateRackTiles(referee.currentPlayer());
		
		this.lblController = lblController;
		this.gameBtnController = gameBtnController;

		this.lblController.currentPlayerLabel().setText(this.referee.currentPlayer().username() + "'s turn");
		
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
			
			// update ihm related content
			this.rackIhm.updateRackTiles(referee.currentPlayer());
			this.gameBtnController.updateExchangeAllBtn(referee);
			this.gameBtnController.updateBuyAnActionBtn(referee);
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
				updateScoreLabel();
				gameboardIhm.update();
				this.rackIhm.updateRackTiles(referee.currentPlayer());
				this.gameBtnController.updateExchangeAllBtn(referee);
				this.gameBtnController.updateBuyAnActionBtn(referee);
			}
		}
	}

	public void endTurnBtnHandler() {        
    	nextTurn();
    }
	
	public void exchangeAllTilesBtnHandler() { // TODO move btn handler to GameBtnController
		Player currentPlayer = referee.currentPlayer();
		if (currentPlayer.isMoveAvailable()) {
			currentPlayer.exchangeAllTheRack();
			Console.message("exchange complete");
			this.rackIhm.updateRackTiles(currentPlayer);
			currentPlayer.setMoveAvailable(false);
			
			this.gameBtnController.updateExchangeAllBtn(referee);
			this.gameBtnController.updateBuyAnActionBtn(referee);
		}
	}
	
	public void buyANewActionBtnHandler() {
		try {
			this.referee.currentPlayer().buyANewAction(referee);
		} catch (ShouldNotBePossibleException e) {
			Console.message(e.getMessage());
		}
		this.gameBtnController.updateExchangeAllBtn(referee);
		this.gameBtnController.updateBuyAnActionBtn(referee);
		this.updateScoreLabel();
		
	}

	public void updateScoreLabel() {
		int currentPlayerIndex = referee.currentPlayerIndex();
		this.lblController.scorePlayerLabels().get(currentPlayerIndex).setText("Score " + referee.currentPlayer().username() + " : " + referee.currentPlayer().points());
	}
	
}
