package latice.gui.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import latice.gui.model.GameBoardIhm;
import latice.gui.model.RackIhm;
import latice.model.GameBoard;
import latice.model.Player;

public class GameController {
	private final GameBoard gameboard;
	private final GameBoardIhm gameboardIhm;
	private LinkedHashMap<Player, RackIhm> playerList;
	private Player currentPlayer;
	private int currentPlayerIndex = 0;
	private int roundCount = 0;
	
	public GameController(GameBoard gameboard, GameBoardIhm gameboardIhm, ArrayList<Player> playerList) {
		this.gameboard = gameboard;
		this.gameboardIhm = gameboardIhm;
		
		for (Player player: playerList) {
			RackIhm newRackIhm = new RackIhm(player.rack());
			newRackIhm.bindController(this);
			this.playerList.put(player, newRackIhm);
			
		}
		
		this.gameboardIhm.bindController(this);
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
		
		this.currentPlayer = new ArrayList<>(this.playerList.keySet()).get(this.currentPlayerIndex);
		
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
