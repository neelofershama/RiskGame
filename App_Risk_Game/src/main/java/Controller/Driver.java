package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Board.Tile;
import App_Risk_Game.src.main.java.Model.Players.Player;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Mutesham
 * @version 1.0
 */


public class Driver {

    /**
     * A list of players
     */
    private static ArrayList<Player> players = null;

    /**
     * stores the number of players
     */
    private static int num_players;

    /**
     * scanner is initialised
     */
    private static Scanner sc = new Scanner(System.in);
    /**
     * List of continents
     */
    static HashMap<String, Integer> continents = new HashMap<String, Integer>();
    /**
     * Board initialised
     */
    static Board board = new Board();


    /**
     * Game driver to start game
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Player player = new Player();

//		System.out.println("Welcome to the Risk Game!");
//		System.out.println("Please enter how would you like to create a map for the game");
//		System.out.println("1. Create a new map OR 2. Load from file ");
//
//		if (sc.hasNextInt()) {
//			int choice = sc.nextInt();
//
//			switch (choice) {
//
//				case 1: {
//					createMap();
//				}
//				break;
//				case 2: {
//					loadMap();
//
//				}
//				break;
//
//			}
//		} else {
//			System.out.println(" Invalid choice");
//		}


        startGame();
    }


    private static void startGame() {
        //initial setup
        //UiTest.main(null);
    }


    private static void getPlayers() {
        if (!(num_players < 2 && num_players > 6)) {
            players = new ArrayList<>(num_players);
            getPlayerDetails();
        } else
            System.out.println("Invalid Number of players, Please select between 2 to 6 player");
    }


    private static void getPlayerDetails() {
        // TODO Auto-generated method stub

    }


}
