package latice.gui.controller;

import javafx.scene.control.Button;
import latice.model.Player;

public class GameBtnController {
	private Button buyANewActionBtn;
	
	public GameBtnController(Button buyANewActionBtn) {
       this.buyANewActionBtn = buyANewActionBtn;
    }

	public void updateBuyAnActionBtn(boolean isFreeMoveAvailable, Player player) {
		if (!isFreeMoveAvailable && player.points() >= 2) {
			buyANewActionBtn.setDisable(false);
		} else {
			buyANewActionBtn.setDisable(true);
		}
	}

	public Button buyANewActionBtn() {
		return buyANewActionBtn;
	}
	
}
