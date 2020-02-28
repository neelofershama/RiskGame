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
	 * stores the number of players
	 */
	private static int num_players;
	
	
	/**
	 * Game driver to start game 
	 * @param args
	 */
	public static void main(String[] args) {
		Player player=new Player();
		startGame();				
	}


	private static void startGame() {
	//initial setup
	}
	
	private static void getPlayers() {
		if(!(num_players < 2 && num_players > 6))
		{
			 players = new ArrayList<>(num_players);
			 getPlayerDetails();
			 }
		else
			System.out.println("Invalid Number of players, Please select between 2 to 6 player");
		}


	private static void getPlayerDetails() {
		// TODO Auto-generated method stub
		
	}
		

}
