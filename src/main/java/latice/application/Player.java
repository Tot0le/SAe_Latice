package latice.application;

public class Player {
	
	private Integer points ;
	private Rack rack ;
	
	public void buyANewAction(Integer points) {
		points = points - 2;
		// TODO    Implémenter une méthode permettant de rakjouter une action au joueur
}
	public void exchangeTheRack() {
		//TODO tilesAmount -5 puis tiles Amount +5  
}
	public void placeATile() {
		//TODO do a -1 on rack - tiles amount
}
	public void DrawATile() {
		//TODO  do a + 1  on rack - tiles amouts
}
	public void passTheTurn() {
		//TODO Faire un compteur total pour le tour du nombre de joueurs et quand celui-ci = nb de joueur alors cycles = +1
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
