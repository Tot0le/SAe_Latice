package latice.gui.controller;

import javafx.scene.control.Button;
import latice.gui.Console;
import latice.gui.model.RackIhm;
import latice.model.Player;
import latice.model.Referee;
import latice.util.ShouldNotBePossibleException;

public class GameBtnController {
	private Referee referee;
	private Button exchangeAllTilesBtn;
	private Button exchangeSelectedTilesBtn;
	private Button confirmBtn;
	private Button buyANewActionBtn;
	private Button endTurnBtn;
	
	public GameBtnController(Referee referee, Button exchangeAllTilesBtn, Button exchangeSelectedTilesBtn, Button confirmBtn, Button buyANewActionBtn, Button endTurnBtn) {
		this.referee = referee;
		this.exchangeAllTilesBtn = exchangeAllTilesBtn;
		this.exchangeSelectedTilesBtn = exchangeSelectedTilesBtn;
		this.confirmBtn = confirmBtn;
		this.buyANewActionBtn = buyANewActionBtn;
		this.endTurnBtn = endTurnBtn;
    }
	
	public void updateAllBtns() {
		updateExchangeAllBtn();
		updateExchangeSelectedBtn();
		updateConfirmBtn();
		updateBuyAnActionBtn();
	}

	public void updateBuyAnActionBtn() {
		Player currentPlayer = referee.currentPlayer();
		boolean isMoveAvailable = currentPlayer.isMoveAvailable();
		if (!isMoveAvailable && currentPlayer.points() >= referee.priceNewAction()) {
			buyANewActionBtn.setDisable(false);
		} else {
			buyANewActionBtn.setDisable(true);
		}
	}

	public void buyANewActionBtnHandler(LabelController lblController) {
		try {
			this.referee.currentPlayer().buyANewAction(referee);
		} catch (ShouldNotBePossibleException e) {
			Console.message(e.getMessage());
		}
		this.updateAllBtns();
		lblController.updateScoreLabel();
		
	}
	
	public Button buyANewActionBtn() {
		return buyANewActionBtn;
	}
	
	public void updateExchangeAllBtn() {
		if (referee.currentPlayer().isMoveAvailable()) {
			exchangeAllTilesBtn.setDisable(false);
		} else {
			exchangeAllTilesBtn.setDisable(true);
		}
	}
	
	public void exchangeAllTilesBtnHandler(RackIhm rackIhm) {
		Player currentPlayer = referee.currentPlayer();
		if (currentPlayer.isMoveAvailable()) {
			currentPlayer.exchangeAllTheRack();
			rackIhm.updateRackTiles(currentPlayer);
			currentPlayer.setMoveAvailable(false);
			
			this.updateExchangeAllBtn();
			this.updateBuyAnActionBtn();
		}
	}

	public void updateExchangeSelectedBtn() {
		if (referee.currentPlayer().isMoveAvailable()) {
			exchangeSelectedTilesBtn.setDisable(false);
		} else {
			exchangeSelectedTilesBtn.setDisable(true);
		}
	}

	public void updateConfirmBtn() {
		if (referee.currentPlayer().isMoveAvailable()) {
			confirmBtn.setDisable(false);
		} else {
			confirmBtn.setDisable(true);
		}
	}
	
	public void disableButtons(boolean lockEndTurn) {
		exchangeAllTilesBtn.setDisable(true);
		exchangeSelectedTilesBtn.setDisable(true);
		confirmBtn.setDisable(true);
		buyANewActionBtn.setDisable(true);
		endTurnBtn.setDisable(lockEndTurn);
	}
	
}
