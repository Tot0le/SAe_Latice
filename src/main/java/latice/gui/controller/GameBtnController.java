package latice.gui.controller;

import javafx.scene.control.Button;
import latice.model.Player;
import latice.model.Referee;

public class GameBtnController {
	private Button exchangeAllTilesBtn;
	private Button buyANewActionBtn;
	
	public GameBtnController(Button exchangeAllTilesBtn, Button buyANewActionBtn) {
		this.exchangeAllTilesBtn = exchangeAllTilesBtn;
		this.buyANewActionBtn = buyANewActionBtn;
    }

	public void updateBuyAnActionBtn(Referee referee) {
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
	
	public void updateExchangeAllBtn(Referee referee) {
		boolean isMoveAvailable = referee.currentPlayer().isMoveAvailable();
		if (isMoveAvailable) {
			exchangeAllTilesBtn.setDisable(false);
		} else {
			exchangeAllTilesBtn.setDisable(true);
		}
	}
	
}
