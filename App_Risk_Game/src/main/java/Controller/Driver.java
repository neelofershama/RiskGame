package App_Risk_Game.src.main.java.Controller;

import java.util.ArrayList;


import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Board.Tile;
import src.main.java.Model.Players.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
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
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		Player player=new Player();

		System.out.println("Welcome to the Risk Game!");
		System.out
				.println("Please enter how would you like to create a map for the game");
		System.out.println("1. Create a new map OR 2. Load from file ");

		if (sc.hasNextInt()) {
			int choice = sc.nextInt();

			switch (choice) {

				case 1: {
					createMap();
				}
				break;
				case 2: {
					loadMap();

				}
				break;

			}
		} else {
			System.out.println(" Invalid choice");
		}


		startGame();				
	}


	private static void startGame() {
	//initial setup
	}

	/**
	 * Method is used to load the map from an existing file
	 * @throws FileNotFoundException
	 */

	public static void loadMap() throws FileNotFoundException {

		System.out.println(" Enter map file address");
		String path = sc.next();

		// ---------------- Reading file-------------------------------
		File file_map = new File(path.trim());
		if (file_map.exists()) {
			Scanner myReader = new Scanner(file_map);

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();

				if ("[Continents]".matches(data)) {

					String continent = myReader.nextLine();
					while (continent != "") {
						String split[] = continent.split("=");
						String continent_name = split[0];
						String no_of_countries = split[1];
						continents.put(continent_name,
								Integer.parseInt(no_of_countries));
					}
				}
				board.setContinents(continents);

				if ("[Territories]".matches(data)) {

					String country_input = myReader.nextLine();
					String split[] = country_input.split(",");
					String country = split[0];
					int x = Integer.parseInt(split[1]);
					int y = Integer.parseInt(split[2]);
					String continent = split[3];
					List<String> neighbours = new ArrayList<>();

					for (int i = 4; i < split.length; i++) {
						neighbours.add(split[i]);
					}

					board.createTile(country, x, y, continent);
					board.setNeighbourTile(neighbours, country);

				}

				System.out.println("Risk Map Loaded!");

			}

			HashMap<String, Tile> map = board.getTiles();
			Iterator i = map.keySet().iterator();

			while (i.hasNext()) {

				String country = i.next().toString();
				Tile country_details = map.get(country);

				System.out.println();
				System.out.println();
				System.out.println("Territory : " + country);
				System.out.println(country_details.toString());
			}

		} else {
			System.out.println("File does not exist! ");
		}

	}

	/**
	 * Method is used to create a map via console by taking arguments from the gamer
	 * @throws IOException
	 */
	public static void createMap() throws IOException {

		int no_of_continents;

		System.out.println("Enter the number of Continents");
		if (sc.hasNextInt()) {
			no_of_continents = sc.nextInt();

			for (int i = 0; i < no_of_continents; i++) {

				System.out.println("Enter continent name");
				String continent = sc.next();

				System.out.println("Enter no of countries in continent");
				int no_of_countries = sc.nextInt();

				continents.put(continent, no_of_countries);
			}
		} else {
			System.out.println("Invalid input! Enter again :");
			sc.next();
		}

		System.out.println("Enter no of countries");
		int no_of_countries = sc.nextInt();
		List<String> neighbours = new ArrayList<>();

		for (int i = 0; i < no_of_countries; i++) {

			System.out.println("Enter country name");
			String country = sc.next();

			System.out.println("Enter x and y co-ordinate");
			int x = sc.nextInt();
			int y = sc.nextInt();

			System.out.println("Enter continent it belongs to");
			String continent = sc.next();

			System.out.println("Enter no of adjacent countries");
			int no_adj_c = sc.nextInt();
			while (no_adj_c != 0) {
				System.out.println("Enter adjacent country");
				neighbours.add(sc.next());
				no_adj_c--;
			}

			board.createTile(country, x, y, continent);
			board.setNeighbourTile(neighbours, country);

		}

		System.out.println("Risk Map Loaded!");
		System.out.println("Storing to file");
		storeMap();

		HashMap<String, Tile> map = board.getTiles();
		Iterator i = map.keySet().iterator();

		while (i.hasNext()) {

			String country = i.next().toString();
			Tile country_details = map.get(country);

			System.out.println();
			System.out.println();
			System.out.println("Territory : " + country);
			System.out.println(country_details.toString());
		}

	}

	/**
	 * Method is used to write the map to a text file created by gamer.
	 * @throws IOException
	 */
	public static String storeMap() throws IOException {

		FileWriter fileWriter = new FileWriter("Map.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);

		printWriter.println("[Continents]");
		for (String continent : continents.keySet()) {
			printWriter.write(continent + "=" + continents.get(continent));
			printWriter.println();
		}

		printWriter.println("[Territories]");

		HashMap<String, Tile> map = board.getTiles();
		Iterator i = map.keySet().iterator();

		while (i.hasNext()) {

			String country = i.next().toString();
			Tile country_details = map.get(country);

			printWriter.write(country + "," + "-,-," + country_details.getContinent() +",");
			for (int j = 0; j < board.getNeighbourTile(country).size(); j++) {
				printWriter.write(board.getNeighbourTile(country).get(j) + ",");
			}
			printWriter.println();

		}

		fileWriter.close();
		printWriter.close();

		FileReader fir = new FileReader("Map.txt");
		BufferedReader bir = new BufferedReader(fir);
		String toReturn = bir.readLine();
		fir.close();
		bir.close();
		return toReturn;
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
