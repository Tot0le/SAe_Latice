package latice.gui.controller;

import javafx.scene.control.Button;
import latice.gui.Console;
import latice.gui.model.RackIhm;
import latice.model.Player;
import latice.model.Referee;

public class GameBtnController {
	private Referee referee;
	private Button exchangeAllTilesBtn;
	private Button buyANewActionBtn;
	
	public GameBtnController(Referee referee, Button exchangeAllTilesBtn, Button buyANewActionBtn) {
		this.referee = referee;
		this.exchangeAllTilesBtn = exchangeAllTilesBtn;
		this.buyANewActionBtn = buyANewActionBtn;
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

	public Button buyANewActionBtn() {
		return buyANewActionBtn;
	}
	
	public void updateExchangeAllBtn() {
		boolean isMoveAvailable = referee.currentPlayer().isMoveAvailable();
		if (isMoveAvailable) {
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
	
}
