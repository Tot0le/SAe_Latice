package latice.gui.controller;

import java.util.List;

import javafx.scene.control.Label;

public class LabelController {
	private List<Label> scorePlayerLabels;
	private Label currentPlayerLabel;
	private Label endMessageLbl;
	
	public LabelController(List<Label> scorePlayerLabels, Label currentPlayerLabel, Label endMessageLbl) {
		this.setScorePlayerLabels(scorePlayerLabels);
		this.setCurrentPlayerLabel(currentPlayerLabel);
		this.setEndMessageLbl(endMessageLbl);
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
}
