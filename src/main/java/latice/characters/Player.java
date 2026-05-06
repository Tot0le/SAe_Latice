package latice.characters;

import latice.application.Pool;
import latice.application.Rack;

public class Player {
	
	private Integer points;
	private Rack rack;
	private Pool pool;
	
	public void buyANewAction(Integer points) {
		points = points - 2;
		// TO DO    Implémenter une méthode permettant de rakjouter une action au joueur
	}
	public void exchangeTheRack() {
		//TO DO tilesAmount -5 puis tiles Amount +5
		
	}
	public void placeATile() {
		//TO DO do a -1 on rack - tiles amount
		
	}
	public void DrawATile() {
		//TO DO  do a + 1  on rack - tiles amouts
		
	}
	public void passTheTurn() {
		//TO DO Faire un compteur total pour le tour du nombre de joueurs et quand celui-ci = nb de joueur alors cycles = +1
	}
	
	// getteurs : 
	public Integer Points() {
		return points;
	}
	
	// setteurs : 
	public void player(Integer points) {
		this.points = points;
	}
}
