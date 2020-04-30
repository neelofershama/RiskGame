package App_Risk_Game.src.test.java.model;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Cards.Card;
import App_Risk_Game.src.main.java.Model.Cards.CardsCollection;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FunctionalTests {
    /**
     * a Hashmap to store the list of continents and the No. of countries in them
     */
    public HashMap<String, Integer> Continents;
    public Board board;

    /**
     * a List to store the neighbours
     */
    List<String> neighbours;

    /**
     * a Hashmap to store the tile and the list of neighbours to it
     */
    HashMap<String, List<String>> neighborsList;
    ArrayList<String> player_names;
@Before
    public void setup(){
        Continents = new HashMap<String, Integer>();
        board =new Board();
        neighbours = new ArrayList<>();
        neighborsList = new HashMap<>();
        Continents.put("Asia",3);
        Continents.put("Europe",2);
        board.setContinents(Continents);
        neighbours.add("India");
        neighbours.add("Pakistan");
        board.createTile("Pakistan", 20, 100, "Asia");
        board.createTile("India", 30, 100, "Asia");
        board.createTile("Sri Lanka", 100, 100, "Asia");
        board.createTile("France",440,550,"Europe");
        neighborsList.put("Sri Lanka",neighbours);
        board.setNeighbourTile( neighbours,"Sri Lanka");
    List<String> territories = new ArrayList<>();
    Iterator iterator = board.getTiles().keySet().iterator();
    while (iterator.hasNext()){
        String name = (String) iterator.next();
        territories.add(name);
        System.out.println(name);
    }
    CardsCollection cardsCollection = new CardsCollection(territories, 5);

     player_names
 = new ArrayList<>();
    player_names.add("p1");
    player_names.add("p2");
    player_names.add("p3");
    int no_of_players = 3;
    PlayerCollectionTest.createPlayers(no_of_players,player_names,new ArrayList<>());
    }

    @Test
    public void phase0Test(){
        Assert.assertEquals(PlayerCollectionTest.players.size(),2);

        CardsCollection.distributeCards(PlayerCollectionTest.players);
        PlayerCollectionTest.players.forEach(x ->
        {
            Assert.assertEquals(x.getTerritories().size(),2);
        });
    }



}
