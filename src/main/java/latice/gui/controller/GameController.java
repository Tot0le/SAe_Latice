package latice.gui.controller;

import java.util.ArrayList;

import latice.gui.model.GameBoardIhm;
import latice.model.GameBoard;
import latice.model.Player;

public class GameController {
	private final GameBoard gameboard;
	private final GameBoardIhm gameboardIhm;
	private ArrayList<Player> playerList;
	
	private Player currentPlayer;
	private int currentPlayerIndex = 0;
	private int roundCount = 0;
	
	public GameController(GameBoard gameboard, GameBoardIhm gameboardIhm, ArrayList<Player> playerList) {
		this.gameboard = gameboard;
		this.gameboardIhm = gameboardIhm;
		this.playerList = playerList;
		
		this.gameboardIhm.bindController(this);
		
		// TODO list of rack and iterate it
//		this.rackIhm.bindController(this);
	}
	
	public void nextRound() {
		
	}
	
	public void nextTurn() {
		
		this.currentPlayerIndex = this.currentPlayerIndex + 1;
		if (this.currentPlayerIndex >= this.playerList.size()) {
			this.roundCount += 1;
			this.currentPlayerIndex = this.currentPlayerIndex % this.playerList.size();
		}
		// TODO check the roundCount number
		
		this.currentPlayer = this.playerList.get(this.currentPlayerIndex);
		
	}
	
	public void playerChoices(Player player) {
		
	}
	
	public void handleTilePlacement() {
		// TODO
	}
	
	public void exchangeTiles() {
		// TODO
	}
	
	public void pass() {
		// TODO
	}

}
