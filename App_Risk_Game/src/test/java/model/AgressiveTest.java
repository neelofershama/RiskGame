package App_Risk_Game.src.test.java.model;

import App_Risk_Game.src.interfaces.Observable;
import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Common.Common;
import App_Risk_Game.src.main.java.Controller.LoadMap;
import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Board.Tile;
import App_Risk_Game.src.main.java.Model.Score.Dice;
import App_Risk_Game.src.main.java.Model.Turns.Turns;
import App_Risk_Game.src.main.java.Model.Cards.*;
import App_Risk_Game.src.main.java.Model.Players.AggressivePlayer;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.interfaces.*;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
public class AgressiveTest {

   public Board board;

    List<String> neighbours;
    ArrayList<String> player_list;
    public HashMap<String, Integer> Continents;
    HashMap<String, List<String>> neighborsList;
    HashMap<String, Integer> territories;
    List<String> continents_owned;
    ArrayList<String> player_behavior;

    String map_owned;

    @Before
    public void setup() {


        Continents = new HashMap<String, Integer>();
        board = new Board();
        neighbours = new ArrayList<>();
        continents_owned = new ArrayList<>();
        neighborsList = new HashMap<>();
        territories = new HashMap<>();
        Continents.put("Asia", 3);
        Continents.put("Europe", 2);
//        board.setContinents(Continents);
        continents_owned.add("asia");
        continents_owned.add("europe");
//        neighbours.add("india");
//        neighbours.add("pakistan");
//        neighborsList.put("srilanka", neighbours);
//        board.setNeighbourTile(neighbours, "srilanka");
        territories.put("india", 1);
        territories.put("pakistan", 2);
        territories.put("srilanka", 3);
        String player_name = "p1";
        String player_color = "blue";
        int player_id = 1;
        Player p = new Player(player_name, player_color, player_id, territories, continents_owned, "a");
        AggressivePlayer a = new AggressivePlayer();




    }

    @Test
    public void phase1Test() {
        Continents.put("Asia", 3);
        Continents.put("Europe", 2);
        board.setContinents(Continents);
        continents_owned.add("asia");
        continents_owned.add("europe");
        territories = new HashMap<>();
//        neighbours.add("india");
//        neighbours.add("pakistan");
//        neighborsList.put("srilanka", neighbours);
//        board.setNeighbourTile(neighbours, "srilanka");
        territories.put("india", 1);
        territories.put("pakistan", 2);
        territories.put("srilanka", 3);
        String player_name = "p1";
        String player_color = "blue";
        int player_id = 1;
        Player p1 = new Player(player_name, player_color, player_id, territories, continents_owned, "a");
        AggressivePlayer a = new AggressivePlayer();
        a.reinforce(p1);

        Assert.assertNotEquals(p1.getTerritories().get("pakistan")+p1.getTerritories().get("india")+p1.getTerritories().get("srilanka"), 6);

    }
//    @Test
//    public void phase2Test() {
//        player_list = new ArrayList<>();
//        player_behavior = new ArrayList<>();
//        Continents.put("Asia", 3);
//        Continents.put("Europe", 2);
//        board.setContinents(Continents);
//        continents_owned.add("asia");
//        continents_owned.add("europe");
//        territories = new HashMap<>();
////        Board b = new Board();
//        LoadMap l = new LoadMap();
//        LoadMap.board.createTile("pakistan", 20, 100, "Asia");
//        LoadMap.board.createTile("india", 40, 100, "Asia");
//        LoadMap.board.createTile("srilanka", 100, 100, "Asia");
//        neighbours.add("india");
//        neighbours.add("pakistan");
//        neighborsList.put("srilanka", neighbours);
//        // System.out.println(neighbours);
//        LoadMap.board.setNeighbourTile(neighbours, "srilanka");
//        territories.put("india", 1);
//        territories.put("pakistan", 2);
//        territories.put("srilanka", 3);
//        player_list.add("p1");
//        player_list.add("p2");
//        player_behavior.add("Aggressive Player");
//        player_behavior.add("Cheater Player");
//
//        String player_name = "p1";
//        String player_color = "blue";
//        int player_id = 1;
//        Player p1 = new Player(player_name, player_color, player_id, territories, continents_owned, "a");
//        Player p2 = new Player("p2", "green", 2, territories, continents_owned, "b");
//        AggressivePlayer a = new AggressivePlayer();
////        PlayerCollectionTest p = new PlayerCollectionTest();
//        PlayerCollectionTest.createPlayers(2,player_list,player_behavior);
//        PlayerCollectionTest.getTurn();
//        a.reinforce(p1);
//        System.out.println("Testing fortyfying phase of aggressive player");
//        System.out.println(p1.getTerritories());
//        a.fortify();
//        System.out.println(p1.getTerritories());
////        a.attack();


//        public HashMap<String, Integer> output;
//        output = new HashMap<>();
//        output = {pakistan=2, india=1, srilanka=2};
//         System.out.println(p1.getTerritories());
//        Assert.assertNotEquals(p1.getTerritories().get("pakistan")+p1.getTerritories().get("india")+p1.getTerritories().get("srilanka"), 5);
////        a.reinforce(p);
//        Assert.assertEquals(a.fortify(), "True");
//        Assert.assertEquals(a.fortify, "True");
//        System.out.println();

    @Test
    public void phase2Test() {
        Continents.put("Asia", 3);
        Continents.put("Europe", 2);
        board.setContinents(Continents);
        continents_owned.add("asia");
        continents_owned.add("europe");
        territories = new HashMap<>();
//        neighbours.add("india");
//        neighbours.add("pakistan");
//        neighborsList.put("srilanka", neighbours);
//        board.setNeighbourTile(neighbours, "srilanka");
        territories.put("india", 1);
        territories.put("pakistan", 2);
        territories.put("srilanka", 3);
        String player_name = "p1";
        String player_color = "blue";
        int player_id = 1;
        Player p1 = new Player(player_name, player_color, player_id, territories, continents_owned, "a");
        AggressivePlayer a = new AggressivePlayer();
        a.reinforce(p1);

        Assert.assertEquals(p1.getTerritories().get("pakistan")+p1.getTerritories().get("india")+p1.getTerritories().get("srilanka"), 6);

    }

}
