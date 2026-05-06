package latice.application;

import java.util.Scanner;

import latice.gui.Console;

public class LaticeApplicationConsole extends Referee{
	private final Integer nbTileMax = 72 ;
	public static void main(String[] args) {
		Console.title("-- Bienvenue dans notre magnifique jeu de latice ! -- ");
		Scanner scan = new Scanner(System.in);
		System.out.println("Veuillez saisir le nombre de joueurs :");
		int nbJoueurs = scan.nextInt();
		System.out.println("Il y a " + nbJoueurs+" joueurs");
	}	

}
