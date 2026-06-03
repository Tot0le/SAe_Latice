package latice.gui.controller;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import latice.model.Referee;

public class LabelController {
	private Referee referee;
	private List<Label> scorePlayerLabels;
	private Label currentPlayerLabel;
	private Label endMessageLbl;
	private Label lblPoolTilesNumber;
	private Label currentTurn;
	
	public LabelController(Referee referee, List<Label> scorePlayerLabels, Label currentPlayerLabel, Label endMessageLbl, Label lblPoolTilesNumber, Label currentTurnLbl) {
		this.referee = referee;
		this.setScorePlayerLabels(scorePlayerLabels);
		this.setCurrentPlayerLabel(currentPlayerLabel);
		this.setEndMessageLbl(endMessageLbl);
		this.setLblPoolTilesNumber(lblPoolTilesNumber);
		this.setCurrentTurn(currentTurnLbl);
		this.updatePoolNumber();
	}

	public void updateScoreLabel() {
		int currentPlayerIndex = referee.currentPlayerIndex();
		String scoreString = "Score " + referee.currentPlayer().username() + " : " + referee.currentPlayer().points();
		this.scorePlayerLabels().get(currentPlayerIndex).setText(scoreString);
		this.scorePlayerLabels().get(currentPlayerIndex).setTooltip(new Tooltip(scoreString));
	}
	
	public void updateCurrentTurn() {
		this.currentTurn.setText(referee.cycleCount() + 1 + "");
	}
	
	public void updatePoolNumber() {
		this.lblPoolTilesNumber.setText("Pool : " + (referee.currentPlayer().pool().tilesAmounts()));
	}
	
	public Label currentPlayerLabel() {
		return currentPlayerLabel;
	}

	public void setCurrentPlayerLabel(Label currentPlayerLabel) {
		this.currentPlayerLabel = currentPlayerLabel;
	}

	public List<Label> scorePlayerLabels() {
		return scorePlayerLabels;
	}

	public void setScorePlayerLabels(List<Label> scorePlayerLabels) {
		this.scorePlayerLabels = scorePlayerLabels;
	}

	public Label endMessageLbl() {
		return endMessageLbl;
	}

	public void setEndMessageLbl(Label endMessageLbl) {
		this.endMessageLbl = endMessageLbl;
	}
	
	public Label currentPlayerScoreLabel(Integer currentPlayerIndex) {
		return this.scorePlayerLabels.get(currentPlayerIndex);
	}

	public Label lblPoolTilesNumber() {
		return lblPoolTilesNumber;
	}

	public void setLblPoolTilesNumber(Label lblPoolTilesNumber) {
		this.lblPoolTilesNumber = lblPoolTilesNumber;
	}

	public Label getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(Label currentTurn) {
		this.currentTurn = currentTurn;
	}
}
