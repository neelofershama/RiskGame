package App_Risk_Game.src.test.java.model.Board;
import App_Risk_Game.src.main.java.Controller.LoadMap;
import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Board.Tile;
import App_Risk_Game.src.main.java.Model.Cards.CardsCollection;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import App_Risk_Game.src.main.java.Model.Score.Dice;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Junit TestCase suite for carrying tests regarding the map elements
 */
public class ContinentTest {
    /**
     * a Hashmap to store the list of continents and the No. of countries in them
     */
    public HashMap<String, Integer> Continents;
  //  public HashMap<String, List<String>> Teritories;
    public Board board;

    /**
     * a List to store the neighbours
     */
    List<String> neighbours;

    List<String> a;

    /**
     * a Hashmap to store the tile and the list of neighbours to it
     */
    HashMap<String, List<String>> neighborsList;


    /**
     * creating an initial setup with the map elements to test.
     */
    @Before
    public void setUp() {
        Continents = new HashMap<String, Integer>();
        board = new Board();
        neighbours = new ArrayList<>();
        neighborsList = new HashMap<>();
        Continents.put("Asia", 3);
        Continents.put("Europe", 2);
        board.setContinents(Continents);
        neighbours.add("India");
        neighbours.add("Pakistan");
        board.createTile("Pakistan", 20, 100, "Asia");
        board.createTile("India", 40, 100, "Asia");
        board.createTile("Sri Lanka", 100, 100, "Asia");
        neighborsList.put("Sri Lanka", neighbours);
        board.setNeighbourTile(neighbours, "Sri Lanka");
//        Teritories = new HashMap<String, Tile>();
//        Teritories.put("Sri Lanka",);

    }

    /**
     * JUnit test case for to verify the Continents and the countries in it
     */
    @Test
    public void checkContinents() throws FileNotFoundException {
        Continents = new HashMap<String, Integer>();
        Continents.put("Asia", 3);
        Continents.put("Europe", 2);
        System.out.println(board.getContinents() );
        System.out.println(Continents);
        assertEquals(board.getContinents(), Continents);


    }

    /**
     * JUnit test case for to verify the neighbours of a particular tile
     */
    @Test
    public void checkNeighbours() {
        List<String> neighbour = new ArrayList<>();
        neighbour.add("India");
        neighbour.add("Pakistan");
        System.out.println(board.getNeighbourTile("Sri Lanka"));
        System.out.println(neighbour);
        assertEquals(board.getNeighbourTile("Sri Lanka"), neighbour);
    }

    /**
     * JUnit test case for checking the size of the neighbours list
     */
    @Test
    public void checkNeighboursSize() {

        assertEquals(board.getNeighbourTile("Sri Lanka").size(), 2);
    }

    @Test
    public void checkTerritories() {
        board.createTile("Pakistan", 20, 100, "Asia");
        board.createTile("India", 40, 100, "Asia");
        board.createTile("Sri Lanka", 100, 100, "Asia");
        neighbours = new ArrayList<>();
        neighbours.add("India");
        neighbours.add("Pakistan");
        board.setNeighbourTile(neighbours, "Sri Lanka");
//
        HashMap<String, Tile> map = board.getTiles();
        Iterator i = map.keySet().iterator();
        while (i.hasNext()) {
//
            String country = i.next().toString();
            Tile country_details = map.get(country);
//
            System.out.println(country + "," + country_details.getXCoordinate() + "," + country_details.getYCoordinate() + "," + country_details.getContinent());
            for (int j = 0; j < board.getNeighbourTile(country).size(); j++) {
                System.out.println("," + board.getNeighbourTile(country).get(j));
            }

        }

   }
//    @Test
//    public void checkPlayer() {
//
//        List<Player> players = PlayerCollection.players;
//        HashMap<String,Integer> territories = new HashMap<>();
//        ArrayList<String> player_names = new ArrayList<>();
//        player_names.add("me");
//        player_names.add("him");
//        PlayerCollection.createPlayers("2", player_names);
//        Player p1 = players.get(0);
//        Player p2 = players.get(1);
//        p1.setTerritories("India", 3);
//        p2.setTerritories("Pakistan", 2);
//        p1.setTerritories("Sri Lanka", 1);
//        p2.setTerritories("Sri Lanka", 1);
//        Dice dice = new Dice();
//        //the troups are not getting reduced so thats the reason we are checking this condition
//        List<Integer> troopslost = dice.rollDice(1,1);
//        boolean val = false;
//        if (troopslost != null)
//            val = true;
//        System.out.println(troopslost);
//        assertFalse(val);
//
//    }

//        for (Player p:players
//        ) {
//            System.out.println(p.getName());
//            System.out.println(p.getTerritories());
//        }
//        Player p = players.get(0);
////        PlayerCollection p = new PlayerCollection();
//        PlayerCollection.createPlayers(play.size(), play);

//
//    }

//
////    Public void CheckDistribution() {
////        CardsCollection cardsCollection = new CardsCollection(territories, 2);
//        List<Player> players = PlayerCollection.players;
//    }
//    Public void CheckDice() {
//
//    }
}