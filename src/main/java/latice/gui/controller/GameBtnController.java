package latice.gui.controller;

import javafx.scene.control.Button;
import latice.model.Player;
import latice.model.Referee;

public class GameBtnController {
	private Button buyANewActionBtn;
	
	public GameBtnController(Button buyANewActionBtn) {
       this.buyANewActionBtn = buyANewActionBtn;
    }

	public void updateBuyAnActionBtn(Referee referee) {
		Player currentPlayer = referee.currentPlayer();
		boolean isFreeMoveAvailable = currentPlayer.isMoveAvailable();
		if (!isFreeMoveAvailable && currentPlayer.points() >= referee.priceNewAction()) {
			buyANewActionBtn.setDisable(false);
		} else {
			buyANewActionBtn.setDisable(true);
		}
	}

	public Button buyANewActionBtn() {
		return buyANewActionBtn;
	}
	
}
