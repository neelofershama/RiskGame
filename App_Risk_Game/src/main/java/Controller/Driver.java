package src.main.java.Controller;

import java.util.ArrayList;

import src.main.java.Model.Players.Player;

/**
 * @author Mutesham
 * @version 1.0
 */


public class Driver {

	/**
	 * A list of players
	 */
	private static ArrayList<Player> players= null;
	
	
	/**
	 * Game driver to start game 
	 * @param args
	 */
	public static void main(String[] args) {
		startGame();
		getPlayers(3);
				
	}


	private static void startGame() {
		Player player=new Player();
	}
	
	private static void getPlayers(int num) {
		int num_players = num;
		if(!(num_players < 2 && num_players > 6))
		{
			 players = new ArrayList<>(num_players);	 
			 }
		else
			System.out.println("Invalid Number of players, Please select between 2 to 6 player");
		}
		

}
